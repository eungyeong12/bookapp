package jo.openapi.bookapp.service;

import jo.openapi.bookapp.dto.BestsellerRequestVo;
import jo.openapi.bookapp.dto.BestsellerResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BookService {

    public BestsellerResponseVo getBestsellerInfo(BestsellerRequestVo bookRequestVo) {
        //http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=[TTBKey]&QueryType=ItemNewAll&MaxResults=10&start=1&SearchTarget=Book&output=xml&Version=20131101

        WebClient webClient = WebClient.builder()
                .baseUrl("http://www.aladin.co.kr/ttb/api")
                .build();

        String apiUrl = "/ItemList.aspx?ttbkey={ttbkey}&QueryType={QueryType}&MaxResults={MaxResults}&start={start}&SearchTarget={SearchTarget}&output={output}&Version={Version}";
        BestsellerResponseVo bookResponse = webClient.get()
                .uri(apiUrl, bookRequestVo.getTtbkey(), bookRequestVo.getQueryType(), bookRequestVo.getMaxResults(), bookRequestVo.getStart(), bookRequestVo.getSearchTarget(), bookRequestVo.getOutput(), bookRequestVo.getVersion())
                .retrieve()
                .bodyToMono(BestsellerResponseVo.class)
                .block();

        return bookResponse;
    }
}
