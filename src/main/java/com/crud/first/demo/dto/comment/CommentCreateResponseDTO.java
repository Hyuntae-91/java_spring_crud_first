package com.crud.first.demo.dto.comment;

import com.crud.first.demo.entity.Comment;

public class CommentCreateResponseDTO extends CommentBasicResponseDTO {
    public CommentCreateResponseDTO(Comment comment) { super(comment); }
}
