package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.dto.film.FilmDto;
import ru.yandex.practicum.filmorate.dao.dto.film.FilmMapper;
import ru.yandex.practicum.filmorate.dao.dto.film.NewFilmRequest;
import ru.yandex.practicum.filmorate.dao.dto.film.UpdateFilmRequest;
import ru.yandex.practicum.filmorate.dao.repository.FilmRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;
    private final GenreService genreService;
    private final LikeService likeService;
    private final MpaService mpaService;

    public FilmDto create(NewFilmRequest request) {
        log.info("Creating film name={}", request.getName());

        mpaService.validateMpaExists(request.getMpa());
        genreService.validateGenresExist(request.getGenres());

        Film film = filmMapper.mapToFilm(request);
        film = filmRepository.create(film);

        Set<Long> genres = request.getGenres();
        film.setGenres(genres);
        genreService.saveByFilm(film.getId(), genres);

        return filmMapper.mapToFilmDto(film);
    }

    public FilmDto update(UpdateFilmRequest request) {
        log.info("Updating film id={}", request.getId());

        Film existingFilm = filmRepository.getById(request.getId())
                .orElseThrow(() ->
                        new NotFoundException("Film with id " + request.getId() + " not found")
                );

        mpaService.validateMpaExists(request.getMpa());
        genreService.validateGenresExist(request.getGenres());

        Film updatedFilm = filmMapper.updateFilmFields(existingFilm, request);
        updatedFilm = filmRepository.update(updatedFilm);

        Set<Long> genres = request.getGenres();
        updatedFilm.setGenres(genres);
        genreService.saveByFilm(updatedFilm.getId(), genres);

        updatedFilm.setLikes(likeService.getLikesIdsByFilm(request.getId()));

        return filmMapper.mapToFilmDto(updatedFilm);
    }

    public FilmDto getById(long id) {
        log.debug("Get film id={}", id);

        Film film = filmRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("Film with id=" + id + " not found"));
        return filmMapper.mapToFilmDto(updateCollections(film));
    }

    public Collection<FilmDto> getAll() {
        log.debug("Get all films");
        return filmRepository.getAll().stream()
                .map(this::updateCollections)
                .map(filmMapper::mapToFilmDto)
                .collect(Collectors.toList());
    }

    public Collection<FilmDto> getPopularFilms(Integer genreId, Integer year, int count) {
        log.debug("Get top {} films", count);

        return filmRepository.getPopularFilms(genreId, year, count).stream()
                .map(this::updateCollections)
                .map(filmMapper::mapToFilmDto)
                .toList();
    }

    private Film updateCollections(Film film) {
        long id = film.getId();
        film.setGenres(genreService.getGenresIdByFilm(id));
        film.setLikes(likeService.getLikesIdsByFilm(id));
        return film;
    }
}