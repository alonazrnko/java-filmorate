package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.service.LikeService;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable long id, @PathVariable long userId) {
        log.debug("Add like from user id={}", id);
        likeService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable long id, @PathVariable long userId) {
        likeService.removeLike(id, userId);
    }
}
