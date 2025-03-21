package com.crud.first.demo.controller;

import com.crud.first.demo.dto.board.*;
import com.crud.first.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public BoardBasicResponseDTO createBoard(@Valid @RequestBody BoardCreateRequestDTO requestDTO) {
        return boardService.createBoard(requestDTO);
    }

    @GetMapping
    public BoardPageResponseDTO getBoardsList(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        return boardService.getAllBoards(page, size);
    }

    @GetMapping("/{id}")
    public BoardDetailResponseDTO getBoardDetail(
        @PathVariable Integer id
    ) {
        return boardService.getBoardDetail(id);
    }

    @PutMapping("/{id}")
    public BoardModifyResponseDTO modifyBoard(
        @PathVariable Integer id,
        @Valid @RequestBody BoardModifyRequestDTO requestModifyDTO
    ) {
        return boardService.modifyBoard(id, requestModifyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Integer id) { boardService.deleteBoard(id); }
}
