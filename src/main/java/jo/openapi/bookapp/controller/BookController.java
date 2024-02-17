package jo.openapi.bookapp.controller;

import jo.openapi.bookapp.dto.*;
import jo.openapi.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

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


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/book/search")
    public String search() {
        return "search";
    }

    @GetMapping("/book/searchResult")
    public String searchResult(@RequestParam(value = "keyword") String keyword, Model model) {
        SearchRequestVo searchRequestVo = new SearchRequestVo();
        searchRequestVo.setTtbkey("");
        searchRequestVo.setQuery(keyword);
        searchRequestVo.setQueryType("Title");
        searchRequestVo.setMaxResults("20");
        searchRequestVo.setStart("1");
        searchRequestVo.setSearchTarget("Book");
        searchRequestVo.setOutput("js");
        searchRequestVo.setVersion("20131101");

        SearchResponseVo searchResponseVo = bookService.getSearchResult(searchRequestVo);
        ArrayList<SearchBookInfo> bookList = searchResponseVo.getItem();
        model.addAttribute("title", searchResponseVo.getTitle());
        model.addAttribute("bookList", bookList);
        return "search";
    }

}
