package com.crud.first.demo.service;

import com.crud.first.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) { this.commentRepository = commentRepository; }
}
