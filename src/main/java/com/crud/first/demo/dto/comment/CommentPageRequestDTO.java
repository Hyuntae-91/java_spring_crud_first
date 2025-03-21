package com.crud.first.demo.dto.comment;

public class CommentPageRequestDTO {
    private int boardId;
    private int page = 0;
    private int size = 20;

    public int getBoardId() {
        return boardId;
    }
    public int getPage() {
        return page;
    }
    public int getSize() {
        return size;
    }
}
