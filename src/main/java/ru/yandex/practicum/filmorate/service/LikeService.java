package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.dto.like.LikeMapper;
import ru.yandex.practicum.filmorate.dao.repository.FilmRepository;
import ru.yandex.practicum.filmorate.dao.repository.LikeRepository;
import ru.yandex.practicum.filmorate.dao.repository.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Like;
import ru.yandex.practicum.filmorate.model.enums.EventOperation;
import ru.yandex.practicum.filmorate.model.enums.EventType;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final FilmRepository filmRepository;
    private final EventService eventService;


    public void addLike(long filmId, long userId) {
        log.info("Add like filmId={} userId={}", filmId, userId);

        Set<Long> userLikes = likeRepository.findUserIdsByFilmId(filmId);
        if (userLikes.contains(userId)) {
            Like like = new Like(filmId, userId);
            LikeMapper.mapToLikeDto(like);
        }

        Like like = new Like(filmId, userId);
        likeRepository.addLike(like);

        LikeMapper.mapToLikeDto(like);

        eventService.addEvent(
                userId,
                EventType.LIKE,
                EventOperation.ADD,
                filmId
        );
    }

    public void removeLike(long filmId, long userId) {
        log.info("Removing like: filmId={}, userId={}", filmId, userId);

        filmRepository.getById(filmId);
        userRepository.getById(userId)
                .orElseThrow(() -> {
                    log.warn("User not found: id={}", userId);
                    return new NotFoundException("User with id " + userId + " not found");
                });

        likeRepository.removeLike(filmId, userId);

        eventService.addEvent(
                userId,
                EventType.LIKE,
                EventOperation.REMOVE,
                filmId
        );
    }

    public Set<Long> getLikesIdsByFilm(long filmId) {
        return likeRepository.findUserIdsByFilmId(filmId);
    }
}
