package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ua.uesf.exception.NotFoundException;
import org.ua.uesf.model.News;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;
import org.ua.uesf.service.news.NewsService;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<GeneralNewsDTO>> getNews(@RequestHeader(value = "locale", defaultValue = "UA") String locale,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(required = false) Long gameId) {
        List<GeneralNewsDTO> newsPage = null;
        if (gameId != null) {
            newsPage = newsService.findNewsByGameId(locale, page, size, gameId);
        } else if (gameId == null) {
            newsPage = newsService.findNews(locale, page, size);
        }
        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @GetMapping("/news/{id}")
    public News getById(@PathVariable("id") long id) {
        return newsService.findById(id);
    }

    @PostMapping("/news")
    public ResponseEntity<Void> saveNews(@RequestBody NewsDTO newsDTO) {
        newsService.saveNews(newsDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/news/{id}")
    public void deleteNews(@PathVariable String id) {
        newsService.deleteNewsById(Long.parseLong(id));
    }

    @PutMapping("/news/{id}")
    public News updateNews(@RequestBody News news,
                           @PathVariable("id") Long id) {
        if (!Objects.equals(news.getId(), id))
            throw new NotFoundException("The id's do not match " + news.getId() + " " + id);

        return newsService.update(id, news);
    }

}
