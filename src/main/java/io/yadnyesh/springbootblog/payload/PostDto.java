package io.yadnyesh.springbootblog.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Title Should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Title Should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
