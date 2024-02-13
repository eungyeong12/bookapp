package jo.openapi.bookapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BestsellerResponseVo {
    String title;
    String totalResults;
    String itemsPerPage;
    ArrayList<BestsellerBookInfo> item;
}
