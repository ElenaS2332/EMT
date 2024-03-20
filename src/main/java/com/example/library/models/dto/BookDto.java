package com.example.library.models.dto;

import com.example.library.models.Author;
import com.example.library.models.enumerations.CategoryEnum;
import lombok.Data;

@Data
public class BookDto {
    private String name;

    private CategoryEnum category;

    private Long authorId;

    private Integer availableCopies;

}
