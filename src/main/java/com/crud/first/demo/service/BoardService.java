package com.crud.first.demo.service;

import com.crud.first.demo.dto.board.*;
import com.crud.first.demo.entity.Board;
import com.crud.first.demo.entity.Comment;
import com.crud.first.demo.repository.BoardRepository;
import com.crud.first.demo.repository.CommentRepository;
import com.crud.first.demo.utils.PasswordHashingSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public BoardService(BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public BoardBasicResponseDTO createBoard(BoardCreateRequestDTO requestDTO) {
        String salt = PasswordHashingSupport.generateSalt();
        String hashedPassword = PasswordHashingSupport.hashPassword(requestDTO.getPassword(), salt);
        Board board = new Board(
                requestDTO.getTitle(),
                requestDTO.getUsername(),
                requestDTO.getContent(),
                hashedPassword,
                salt
        );
        Board savedBoard = boardRepository.save(board);
        return new BoardBasicResponseDTO(savedBoard);
    }

    public BoardPageResponseDTO getAllBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Board> boardPage = boardRepository.findAllByStatusNot((byte) -1, pageable);

        List<BoardBasicResponseDTO> content = boardPage.getContent()
                .stream()
                .map(BoardBasicResponseDTO::new)
                .toList();

        return new BoardPageResponseDTO(content, boardPage.getTotalPages(), boardPage.getTotalElements());
    }

    public BoardDetailResponseDTO getBoardDetail(Integer id) {
        Board board = boardRepository.findByIdAndStatusNot(id, (byte) -1)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        return new BoardDetailResponseDTO(board);
    }

    public BoardModifyResponseDTO modifyBoard(
            Integer id,
            BoardModifyRequestDTO requestModifyDTO
    ) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        String inputHash = PasswordHashingSupport.hashPassword(requestModifyDTO.getPassword(), board.getPasswordSalt());
        if (!inputHash.equals(board.getPasswordHash())) {
            throw new RuntimeException("Hash does not match");
        }
        board.setTitle(requestModifyDTO.getTitle());
        board.setContent(requestModifyDTO.getContent());
        boardRepository.save(board);

        return new BoardModifyResponseDTO(board);
    }

    public void deleteBoard(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        board.setStatus((byte) -1);
        boardRepository.save(board);

        List<Comment> comments = commentRepository.findAllByBoard_IdAndStatusNot(id, (byte) -1);
        for (Comment comment : comments) {
            comment.setStatus((byte) -1);
        }
        commentRepository.saveAll(comments);
    }
}
