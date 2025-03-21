package com.crud.first.demo.dto.comment;

import com.crud.first.demo.entity.Comment;
import jakarta.validation.constraints.NotBlank;

public class CommentBasicRequestDTO {
    @NotBlank
    private Integer boardId;

    @NotBlank
    private String content;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public CommentBasicRequestDTO(Comment comment) {
        this.content = comment.getContent();
        this.username = comment.getUsername();
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Integer getBoardId() { return boardId; }
    public void setBoardId(Integer boardId) { this.boardId = boardId; }
}
