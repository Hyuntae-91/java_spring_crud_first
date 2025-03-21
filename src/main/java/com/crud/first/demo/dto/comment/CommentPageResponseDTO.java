package com.crud.first.demo.dto.comment;

import java.util.List;

public class CommentPageResponseDTO {
    private List<CommentBasicResponseDTO> content;
    private int totalPages;
    private long totalElements;

    public CommentPageResponseDTO(List<CommentBasicResponseDTO> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<CommentBasicResponseDTO> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
