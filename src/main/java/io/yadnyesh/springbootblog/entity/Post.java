package io.yadnyesh.springbootblog.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String description;
    private String content;
}
