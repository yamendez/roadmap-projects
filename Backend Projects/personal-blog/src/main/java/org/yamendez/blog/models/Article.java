package org.yamendez.blog.models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;

import java.time.LocalDate;

//@JsonSerialize(using = DurationSerializer.class)
public class Article {
    private Long id;
    private String title;
    private LocalDate date;
    private String content;
    private static Long count = 0L;

    public Article() {
        this.id = count + 1;
        count = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
