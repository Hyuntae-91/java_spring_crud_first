package com.crud.first.demo.dto.comment;

import com.crud.first.demo.entity.Comment;

public class CommentCreateRequestDTO extends CommentBasicRequestDTO {
    public CommentCreateRequestDTO(Comment comment) { super(comment); }
}

