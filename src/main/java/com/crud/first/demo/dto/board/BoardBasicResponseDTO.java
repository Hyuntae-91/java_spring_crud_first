package com.crud.first.demo.dto.board;

import com.crud.first.demo.entity.Board;

public class BoardBasicResponseDTO {
    private Integer id;
    private String title;
    private String content;
    private String username;

    public BoardBasicResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUsername();
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getUsername() { return username; }
}

