package com.gitpeek.gitpeek_backend.repository;

import com.gitpeek.gitpeek_backend.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserId(Long userId);
    //Foreign keys in MySQL for each userId to have a reference to a user id in "users"
    Optional<Bookmark> findByUserIdAndRepositoryId(Long userId, Long repositoryId);
}
