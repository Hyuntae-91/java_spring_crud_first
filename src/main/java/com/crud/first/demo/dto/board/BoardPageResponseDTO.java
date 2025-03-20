package com.crud.first.demo.dto.board;

import java.util.List;

public class BoardPageResponseDTO {
    private List<BoardCreateResponseDTO> content;
    private int totalPages;
    private long totalElements;

    public BoardPageResponseDTO(List<BoardCreateResponseDTO> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<BoardCreateResponseDTO> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
