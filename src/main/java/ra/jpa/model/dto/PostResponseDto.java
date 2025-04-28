package ra.jpa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
}
