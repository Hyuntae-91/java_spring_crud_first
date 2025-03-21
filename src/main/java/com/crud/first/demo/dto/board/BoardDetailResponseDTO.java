package com.crud.first.demo.dto.board;

import com.crud.first.demo.entity.Board;

public class BoardDetailResponseDTO extends BoardBasicResponseDTO {
    public BoardDetailResponseDTO(Board board) {
        super(board);
    }
}
