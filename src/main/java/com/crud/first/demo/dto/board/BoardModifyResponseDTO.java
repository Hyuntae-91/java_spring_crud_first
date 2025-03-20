package com.crud.first.demo.dto.board;

import com.crud.first.demo.entity.Board;

public class BoardModifyResponseDTO {
    private String title;
    private String content;
    private String username;

    public BoardModifyResponseDTO(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUsername();
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getUsername() { return username; }
}
