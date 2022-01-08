package io.yadnyesh.springbootblog.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
