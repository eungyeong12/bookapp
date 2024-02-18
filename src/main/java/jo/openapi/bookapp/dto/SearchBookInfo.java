package jo.openapi.bookapp.dto;

import lombok.*;

import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchBookInfo {
    String title;
    String author;
    String description;
    String cover;
}
