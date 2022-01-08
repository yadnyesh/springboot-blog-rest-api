package io.yadnyesh.springbootblog.payload;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String comment;
}
