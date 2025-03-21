package com.crud.first.demo.dto.comment;

import com.crud.first.demo.entity.Comment;

public class CommentModifyRequestDTO extends CommentBasicRequestDTO {
    public CommentModifyRequestDTO(Comment comment) { super(comment); }
}
