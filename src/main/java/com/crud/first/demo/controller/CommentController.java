package com.crud.first.demo.controller;

import com.crud.first.demo.dto.comment.*;
import com.crud.first.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping
    public CommentCreateResponseDTO createComment(CommentCreateRequestDTO commentCreateRequestDTO) {
        return commentService.createComment(commentCreateRequestDTO);
    }

    @GetMapping
    public CommentPageResponseDTO getComments(CommentPageRequestDTO commentPageRequestDTO) {
        return commentService.getAllComments(commentPageRequestDTO);
    }

    @PutMapping("/{id}")
    public CommentModifyResponseDTO modifyComment(
        @PathVariable Integer id,
        CommentModifyRequestDTO commentModifyRequestDTO
    ) {
        return commentService.modifyComment(id, commentModifyRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

}
