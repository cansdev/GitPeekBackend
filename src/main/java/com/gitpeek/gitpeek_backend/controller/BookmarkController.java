package com.gitpeek.gitpeek_backend.controller;

import com.gitpeek.gitpeek_backend.entity.Bookmark;
import com.gitpeek.gitpeek_backend.payload.ResponseDto;
import com.gitpeek.gitpeek_backend.service.BookmarkService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<ResponseDto> addBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        Bookmark bookmark = bookmarkService.addBookmark(
                bookmarkRequest.getUserId(),
                bookmarkRequest.getRepositoryId(),
                bookmarkRequest.getStars(),
                bookmarkRequest.getDescription()
        );
        ResponseDto response = new ResponseDto("Bookmark added successfully!", HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Bookmark>> getBookmarks(@PathVariable Long userId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUserId(userId);
        return new ResponseEntity<>(bookmarks, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{repositoryId}")
    public ResponseEntity<ResponseDto> getBookmark(@PathVariable Long userId, @PathVariable Long repositoryId) {
        Optional<Bookmark> bookmark = bookmarkService.getBookmark(userId, repositoryId);
        if (bookmark.isPresent()) {
            ResponseDto response = new ResponseDto("Bookmark found!", HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseDto response = new ResponseDto("Bookmark not found!", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/{repositoryId}")
    public ResponseEntity<ResponseDto> deleteBookmark(@PathVariable Long userId, @PathVariable Long repositoryId) {
        bookmarkService.deleteBookmark(userId, repositoryId);
        ResponseDto response = new ResponseDto("Bookmark deleted successfully!", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Setter
    @Getter
    public static class BookmarkRequest {
        private Long userId;
        private Long repositoryId;
        private Integer stars;
        private String description;
    }
}
