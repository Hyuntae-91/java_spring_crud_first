package com.crud.first.demo.dto.comment;

import com.crud.first.demo.entity.Comment;

public class CommentModifyResponseDTO extends CommentModifyRequestDTO {
    public CommentModifyResponseDTO(Comment comment) { super(comment); }
}
