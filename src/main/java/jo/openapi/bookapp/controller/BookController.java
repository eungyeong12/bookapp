package jo.openapi.bookapp.controller;

import jo.openapi.bookapp.dto.BestsellerBookInfo;
import jo.openapi.bookapp.dto.BestsellerRequestVo;
import jo.openapi.bookapp.dto.BestsellerResponseVo;
import jo.openapi.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;
    //http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=[TTBKey]&QueryType=ItemNewAll&MaxResults=10&start=1&SearchTarget=Book&output=xml&Version=20131101

    @GetMapping("/book/bestseller")
    public String findBestseller(Model model) {
        BestsellerRequestVo bookRequestVo = new BestsellerRequestVo();
        bookRequestVo.setTtbkey("");
        bookRequestVo.setQueryType("bestseller");
        bookRequestVo.setMaxResults("50");
        bookRequestVo.setStart("1");
        bookRequestVo.setSearchTarget("Book");
        bookRequestVo.setOutput("js");
        bookRequestVo.setVersion("20131101");

        BestsellerResponseVo bookResponseVo = bookService.getBestsellerInfo(bookRequestVo);
        ArrayList<BestsellerBookInfo> bookList = bookResponseVo.getItem();
        model.addAttribute("title", bookResponseVo.getTitle());
        model.addAttribute("bookList", bookList);
        return "list";
    }
}
