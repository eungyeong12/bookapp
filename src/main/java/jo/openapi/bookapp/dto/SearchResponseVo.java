package jo.openapi.bookapp.dto;

import lombok.*;

import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseVo {
    String title;
    String startIndex;
    String totalResults;
    String itemsPerPage;
    String query;
    ArrayList<SearchBookInfo> item;
}
