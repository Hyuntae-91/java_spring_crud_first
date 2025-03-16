package com.crud.first.demo.repository;

import com.crud.first.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatusNot(Byte status);
    Optional<User> findByIdAndStatusNot(Long id, Byte status);
}
