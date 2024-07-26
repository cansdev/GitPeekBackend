package com.gitpeek.gitpeek_backend.service;

import com.gitpeek.gitpeek_backend.entity.Bookmark;
import com.gitpeek.gitpeek_backend.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark addBookmark(Long userId, Long repositoryId, Integer stars, String description, String name) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setRepositoryId(repositoryId);
        bookmark.setStars(stars);
        bookmark.setDescription(description);
        bookmark.setName(name);
        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> getBookmarksByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public Optional<Bookmark> getBookmark(Long userId, Long repositoryId) {
        return bookmarkRepository.findByUserIdAndRepositoryId(userId, repositoryId);
    }

    public void deleteBookmark(Long userId, Long repositoryId) {
        bookmarkRepository.findByUserIdAndRepositoryId(userId, repositoryId)
                .ifPresent(bookmarkRepository::delete);
    }
}
