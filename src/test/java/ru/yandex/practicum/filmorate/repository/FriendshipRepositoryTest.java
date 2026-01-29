package ru.yandex.practicum.filmorate.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.dao.repository.FriendshipRepository;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class FriendshipRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FriendshipRepository friendshipRepository;

    private void insertUser(long id) {
        jdbcTemplate.update(
                "INSERT INTO users (user_id, email, login) VALUES (?, ?, ?)",
                id,
                "user" + id + "@test.com",
                "user" + id
        );
    }

    @Test
    void add_shouldInsertFriendship() {
        insertUser(1L);
        insertUser(2L);

        Friendship friendship = new Friendship(1L, 2L);

        Friendship saved = friendshipRepository.add(friendship);

        assertThat(saved.getUserId()).isEqualTo(1L);
        assertThat(saved.getFriendId()).isEqualTo(2L);

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM friendships WHERE user_id = ? AND friend_id = ?",
                Integer.class,
                1L, 2L
        );

        assertThat(count).isEqualTo(1);
    }

    @Test
    void findAllByUserId_shouldReturnFriends() {
        insertUser(1L);
        insertUser(2L);
        insertUser(3L);

        jdbcTemplate.update(
                "INSERT INTO friendships (user_id, friend_id) VALUES (?, ?)",
                1L, 2L
        );
        jdbcTemplate.update(
                "INSERT INTO friendships (user_id, friend_id) VALUES (?, ?)",
                1L, 3L
        );

        List<Friendship> result = friendshipRepository.findAllByUserId(1L);

        assertThat(result)
                .hasSize(2)
                .extracting(Friendship::getFriendId)
                .containsExactlyInAnyOrder(2L, 3L);
    }

    @Test
    void delete_shouldRemoveFriendship() {
        insertUser(1L);
        insertUser(2L);

        jdbcTemplate.update(
                "INSERT INTO friendships (user_id, friend_id) VALUES (?, ?)",
                1L, 2L
        );

        friendshipRepository.delete(1L, 2L);

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM friendships WHERE user_id = ? AND friend_id = ?",
                Integer.class,
                1L, 2L
        );

        assertThat(count).isEqualTo(0);
    }

    @Test
    void findAllByUserId_shouldReturnEmptyList_whenNoFriends() {
        insertUser(1L);

        List<Friendship> result = friendshipRepository.findAllByUserId(1L);

        assertThat(result).isEmpty();
    }
}
