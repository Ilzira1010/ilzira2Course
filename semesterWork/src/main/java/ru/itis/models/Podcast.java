package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class Podcast {
    private Long id;
    private User user;
    private String title;
    private String img;
    private String track;
    private Date created_at;
    private Category category;
}
