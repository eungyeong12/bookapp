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
    String pubDate;
    String description;
    String cover;
    String publisher;
}
