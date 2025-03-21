package com.crud.first.demo.dto.board;

import java.util.List;

public class BoardPageResponseDTO {
    private List<BoardBasicResponseDTO> content;
    private int totalPages;
    private long totalElements;

    public BoardPageResponseDTO(List<BoardBasicResponseDTO> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<BoardBasicResponseDTO> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
