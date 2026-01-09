package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Friendship {

    private long userId;     // кто отправил запрос
    private long friendId;   // кому отправили
}