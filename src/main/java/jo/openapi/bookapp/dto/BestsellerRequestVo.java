package jo.openapi.bookapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestsellerRequestVo {
    String ttbkey;
    String QueryType;
    String MaxResults;
    String start;
    String SearchTarget;
    String output;
    String Version;
}
