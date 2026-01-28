package ru.yandex.practicum.filmorate.dao.dto.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Event;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public EventDto mapToEventDto(Event event) {
        EventDto eventDto = new EventDto();

        eventDto.setEventId(event.getEventId());
        eventDto.setTimestamp(event.getTimestamp());
        eventDto.setUserId(event.getUserId());
        eventDto.setEventType(event.getEventType().name());
        eventDto.setOperation(event.getOperation().name());
        eventDto.setEntityId(event.getEntityId());

        return eventDto;
    }
}


