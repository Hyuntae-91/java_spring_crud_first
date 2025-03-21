package com.crud.first.demo.dto.comment;

import com.crud.first.demo.entity.Comment;

public class CommentBasicResponseDTO {
    private Integer id;
    private String content;
    private String username;
    private int boardId;

    public CommentBasicResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.boardId = comment.getBoardId();
    }

    public Integer getId() { return id; }
    public String getContent() { return content; }
    public String getUsername() { return username; }
    public int getBoardId() { return boardId; }
}
