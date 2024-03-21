package com.example.library.models.events;

import com.example.library.models.Book;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class BookCreatedEvent extends ApplicationEvent {

    public BookCreatedEvent(Book source) {
        super(source);
    }

    public BookCreatedEvent(Book source, Clock clock) {
        super(source, clock);
    }
}
