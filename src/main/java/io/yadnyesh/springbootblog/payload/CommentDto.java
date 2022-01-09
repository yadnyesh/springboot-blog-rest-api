package io.yadnyesh.springbootblog.payload;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty
    @Email(message = "Valid email is required")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Comments must be minimum 10 characters in length")
    private String body;
}
