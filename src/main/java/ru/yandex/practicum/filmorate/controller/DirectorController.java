package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dao.dto.director.DirectorDto;
import ru.yandex.practicum.filmorate.dao.dto.director.NewDirectorRequest;
import ru.yandex.practicum.filmorate.service.DirectorService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public Collection<DirectorDto> getAll() {
        log.info("Get all directors");
        return directorService.getAll();
    }

    @GetMapping("/{id}")
    public DirectorDto getById(@PathVariable Long id) {
        log.info("Get director by id={}", id);
        return directorService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto create(@Valid @RequestBody NewDirectorRequest director) {
        log.info("Create director name={}", director.getName());
        return directorService.create(director);
    }

    @PutMapping
    public DirectorDto update(@Valid @RequestBody DirectorDto director) {
        log.info("Update director id={}", director.getId());
        return directorService.update(director);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Delete director id={}", id);
        directorService.delete(id);
    }
}
