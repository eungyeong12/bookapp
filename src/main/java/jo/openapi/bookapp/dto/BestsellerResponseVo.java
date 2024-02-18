package jo.openapi.bookapp.dto;

import lombok.*;

import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BestsellerResponseVo {
    String title;
    String startIndex;
    String totalResults;
    String itemsPerPage;
    ArrayList<BestsellerBookInfo> item;
}
