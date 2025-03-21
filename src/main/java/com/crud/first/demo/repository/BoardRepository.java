package com.crud.first.demo.repository;

import com.crud.first.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findAllByStatusNot(byte status, Pageable pageable);
    Optional<Board> findByIdAndStatusNot(Integer id, byte b);
}
