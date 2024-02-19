package jo.openapi.bookapp.controller;

import jo.openapi.bookapp.dto.*;
import jo.openapi.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Value("${ttbkey}")
    private String ttbkey;

    private final BookService bookService;

    @GetMapping("/book/bestseller")
    public String findBestseller(@PageableDefault(page=1) Pageable pageable, Model model) {
        BestsellerRequestVo bookRequestVo = new BestsellerRequestVo();
        bookRequestVo.setTtbkey(ttbkey);
        bookRequestVo.setQueryType("bestseller");
        bookRequestVo.setMaxResults("50");
        bookRequestVo.setStart(String.valueOf(pageable.getPageNumber()));
        bookRequestVo.setSearchTarget("Book");
        bookRequestVo.setOutput("js");
        bookRequestVo.setVersion("20131101");

        BestsellerResponseVo bookResponseVo = bookService.getBestsellerInfo(bookRequestVo);
        ArrayList<BestsellerBookInfo> bookList = bookResponseVo.getItem();

        int blockLimit = Integer.parseInt(bookResponseVo.getItemsPerPage());
        int startPage = Integer.parseInt(bookResponseVo.getStartIndex());
        int totalResult = Integer.parseInt(bookResponseVo.getTotalResults());
        int endPage = (totalResult%blockLimit == 0) ? (totalResult/blockLimit) : (totalResult/blockLimit + 1);
        int currentPage = pageable.getPageNumber();

        model.addAttribute("title", bookResponseVo.getTitle());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("bookList", bookList);
        model.addAttribute("currentPage", currentPage);
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
    public String searchResult(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") String page, Model model) {
        SearchRequestVo searchRequestVo = new SearchRequestVo();
        searchRequestVo.setTtbkey(ttbkey);
        searchRequestVo.setQuery(keyword);
        searchRequestVo.setQueryType("Title");
        searchRequestVo.setMaxResults("20");
        searchRequestVo.setStart(page);
        searchRequestVo.setSearchTarget("Book");
        searchRequestVo.setOutput("js");
        searchRequestVo.setVersion("20131101");

        SearchResponseVo searchResponseVo = bookService.getSearchResult(searchRequestVo);
        ArrayList<SearchBookInfo> bookList = searchResponseVo.getItem();

        int blockLimit = Integer.parseInt(searchResponseVo.getItemsPerPage());
        int startPage = Integer.parseInt(searchResponseVo.getStartIndex());
        int totalResult = Integer.parseInt(searchResponseVo.getTotalResults());
        int endPage = (totalResult%blockLimit == 0) ? (totalResult/blockLimit) : (totalResult/blockLimit + 1);
        int currentPage = Integer.valueOf(page);
        int nextPage = currentPage + 1;
        String query = searchResponseVo.getQuery();

        model.addAttribute("title", searchResponseVo.getTitle());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("bookList", bookList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("query", query);
        return "search";
    }

}
