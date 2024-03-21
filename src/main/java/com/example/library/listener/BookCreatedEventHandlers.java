package com.example.library.listener;

import com.example.library.models.events.BookCreatedEvent;
import com.example.library.services.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookCreatedEventHandlers {
    private final BookService bookService;

    public BookCreatedEventHandlers(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreatedEvent event){
        System.out.println("Book is created");
    }
}
