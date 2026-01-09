package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> films = new HashMap<>();
    private long nextId = 1;

    @Override
    public Film create(Film film) {
        film.setId(nextId++);
        films.put(film.getId(), film);
        log.debug("Film created: {}", film);
        return film;
    }

    @Override
    public Film update(Film film) {
        films.put(film.getId(), film);
        log.debug("Film updated: {}", film);
        return film;
    }

    @Override
    public Optional<Film> getById(long id) {
        log.debug("Searching film by id={}", id);
        return Optional.ofNullable(films.get(id));
    }

    @Override
    public Collection<Film> getAll() {
        log.debug("Getting all films, count={}", films.size());
        return films.values();
    }

    @Override
    public void addLike(long filmId, long userId) {
        Film film = getById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found"));
        film.getLikes().add(userId);
    }

    @Override
    public void removeLike(long filmId, long userId) {
        Film film = getById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found"));
        film.getLikes().remove(userId);
    }
}


