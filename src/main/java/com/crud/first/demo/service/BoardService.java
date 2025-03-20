package com.crud.first.demo.service;

import com.crud.first.demo.dto.board.*;
import com.crud.first.demo.entity.Board;
import com.crud.first.demo.repository.BoardRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashed = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public BoardCreateResponseDTO createBoard(BoardCreateRequestDTO requestDTO) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(requestDTO.getPassword(), salt);
        Board board = new Board(
                requestDTO.getTitle(),
                requestDTO.getUsername(),
                requestDTO.getContent(),
                hashedPassword,
                salt
        );
        Board savedBoard = boardRepository.save(board);
        return new BoardCreateResponseDTO(savedBoard);
    }

    public BoardPageResponseDTO getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Board> boardPage = boardRepository.findAll(pageable);

        List<BoardCreateResponseDTO> content = boardPage.getContent()
                .stream()
                .map(BoardCreateResponseDTO::new)
                .toList();

        return new BoardPageResponseDTO(content, boardPage.getTotalPages(), boardPage.getTotalElements());
    }

    public BoardModifyResponseDTO modifyBoard(
            Integer id,
            BoardModifyRequestDTO requestModifyDTO
    ) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        String inputHash = hashPassword(requestModifyDTO.getPassword(), board.getPasswordSalt());
        if (!inputHash.equals(board.getPasswordHash())) {
            throw new RuntimeException("Hash does not match");
        }
        board.setTitle(requestModifyDTO.getTitle());
        board.setContent(requestModifyDTO.getContent());
        boardRepository.save(board);

        return new BoardModifyResponseDTO(board);
    }

    public void deleteBoard(Integer id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        optionalBoard.ifPresent(board -> {
            board.setStatus((byte) -1);
            boardRepository.save(board);
        });
    }
}
