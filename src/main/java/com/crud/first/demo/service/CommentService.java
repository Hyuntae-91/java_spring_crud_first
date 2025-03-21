package com.crud.first.demo.service;

import com.crud.first.demo.dto.comment.*;
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
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public CommentCreateResponseDTO createComment(CommentCreateRequestDTO commentCreateRequestDTO) {
        String salt = PasswordHashingSupport.generateSalt();
        String hashedPassword = PasswordHashingSupport.hashPassword(commentCreateRequestDTO.getPassword(), salt);
        Board board = boardRepository.findById(
            commentCreateRequestDTO.getBoardId()).orElseThrow(() -> new RuntimeException("Board not found"));
        Comment comment = new Comment(
            commentCreateRequestDTO.getContent(),
            commentCreateRequestDTO.getUsername(),
            hashedPassword,
            salt,
            board
        );
        Comment savedComment = commentRepository.save(comment);
        return new CommentCreateResponseDTO(savedComment);
    }

    public CommentPageResponseDTO getAllComments(CommentPageRequestDTO commentPageRequestDTO) {
        Board board = boardRepository.findById(
            commentPageRequestDTO.getBoardId()
        ).orElseThrow(() -> new RuntimeException("Board not found"));
        Pageable pageable = PageRequest.of(
            commentPageRequestDTO.getPage(),
            commentPageRequestDTO.getSize(),
            Sort.by(Sort.Direction.DESC, "createdAt")
        );
        Page<Comment> commentPage = commentRepository.findAllByBoard_IdAndStatusNot(
            commentPageRequestDTO.getBoardId(), (byte) -1, pageable
        );

        List<CommentBasicResponseDTO> content = commentPage.getContent()
                .stream()
                .map(CommentBasicResponseDTO::new)
                .toList();

        return new CommentPageResponseDTO(content, commentPage.getTotalPages(), commentPage.getTotalElements());
    }

    public CommentModifyResponseDTO modifyComment(int id, CommentModifyRequestDTO commentModifyRequestDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        String inputHash = PasswordHashingSupport.hashPassword(
            commentModifyRequestDTO.getPassword(),
            comment.getPasswordSalt()
        );
        if (!inputHash.equals(commentModifyRequestDTO.getPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        comment.setContent(commentModifyRequestDTO.getContent());
        Comment savedComment = commentRepository.save(comment);

        return new CommentModifyResponseDTO(savedComment);
    }

    public void deleteComment(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setStatus((byte) -1);
        commentRepository.save(comment);
    }
}
