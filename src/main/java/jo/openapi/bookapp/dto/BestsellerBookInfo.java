package jo.openapi.bookapp.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BestsellerBookInfo {
    String title;
    String author;
    String pubDate;
    String description;
    String cover;
    String publisher;
}
