package org.norklas.norkport;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String date;

    public BlogPost() {

    }

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return "BlogPost [id=" + id + ", title=" + title + ", content=" + content + ", date=" + date + "]";
    }
}
