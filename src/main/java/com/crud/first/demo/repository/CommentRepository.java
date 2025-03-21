package com.crud.first.demo.repository;

import com.crud.first.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findAllByStatusNot(byte status, Pageable pageable);
    List<Comment> findAllByBoard_IdAndStatusNot(int boardId, byte status);
    Page<Comment> findAllByBoard_IdAndStatusNot(int boardId, byte status, Pageable pageable);
    Optional<Comment> findByIdAndStatusNot(Integer id, byte b);
}
