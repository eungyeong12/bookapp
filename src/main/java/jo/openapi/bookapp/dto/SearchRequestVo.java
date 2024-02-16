package jo.openapi.bookapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestVo {
    // ttbkey=[TTBKey]&Query=aladdin&QueryType=Title&MaxResults=10
    // &start=1&SearchTarget=Book&output=xml&Version=20131101
    String ttbkey;
    String Query;
    String QueryType;
    String MaxResults;
    String start;
    String SearchTarget;
    String output;
    String Version;
}
