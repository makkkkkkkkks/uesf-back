package org.ua.uesf.service.news.impl;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.messages.NotFoundException;
import org.ua.uesf.mapper.NewsMapper;
import org.ua.uesf.model.Game;
import org.ua.uesf.model.News;
import org.ua.uesf.model.NewsStatus;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;
import org.ua.uesf.resository.GameRepository;
import org.ua.uesf.resository.NewsRepository;
import org.ua.uesf.service.news.NewsService;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final GameRepository gameRepository;
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);


    @Override
    public List<GeneralNewsDTO> findNews(String locale, Integer page, Integer size) {

        Pageable paging = PageRequest.of(page, size);

        Page<News> news = newsRepository.findAll(NewsStatus.PUBLISHED.toString(), paging);
        return getGeneralNewsDTOs(locale, news);
    }

    @Override
    public List<GeneralNewsDTO> findNewsByGameId(String locale, Integer page, Integer size, Long id) {
        Pageable paging = PageRequest.of(page, size);
        Page<News> news = newsRepository.findAllByGame(1L, NewsStatus.PUBLISHED, paging);
        return getGeneralNewsDTOs(locale, news);
    }


    private List<GeneralNewsDTO> getGeneralNewsDTOs(String locale, Page<News> news) {
        List<GeneralNewsDTO> generalNewsDTOS = null;
        if ("UA".equals(locale)) {
            generalNewsDTOS = news.getContent().stream().map(newsMapper::dtoUA).collect(Collectors.toList());
        }
        if ("EN".equals(locale)) {
            generalNewsDTOS = news.getContent().stream().map(newsMapper::dtoEN).collect(Collectors.toList());
        }
        return generalNewsDTOS;
    }

    @Override
    public void saveNews(NewsDTO newsDTO) {

        newsDTO.setCreationDate(Instant.now());
        newsDTO.setUpdateDate(Instant.now());
        News news = newsMapper.dtoToNews(newsDTO);
        newsDTO.getGame().forEach(gameId -> {
                    if (!gameRepository.findById(Long.valueOf(gameId)).isPresent()) {
                        throw new NotFoundException("Can't find game with id: " + gameId);
                    }
                    Game game = gameRepository.findById(Long.valueOf(gameId)).get();
                    news.getGame().add(game);
                }
        );


        newsRepository.save(news);
    }

    @Override
    public void deleteNewsById(Long id) {
        if (!newsRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find news with id:" + id);
        }
        newsRepository.deleteById(id);
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Can't find news with id:" + id));
    }

    @Override
    public News update(Long id, News news) {

        if (!newsRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find news with id:" + id);
        }

        News newsFromDb = newsRepository.findById(id).get();

        if (Objects.nonNull(news.getTitleUA()) && !"".equalsIgnoreCase(news.getTitleUA())) {
            newsFromDb.setTitleUA(news.getTitleUA());
        }
        if (Objects.nonNull(news.getTitleEN()) && !"".equalsIgnoreCase(news.getTitleEN())) {
            newsFromDb.setTitleEN(news.getTitleEN());
        }

        if (Objects.nonNull(news.getGame()) && !news.getGame().isEmpty()) {
            newsFromDb.setGame(news.getGame());
        }
        if (Objects.nonNull(news.getContentEN()) && !"".equalsIgnoreCase(news.getContentEN())) {
            newsFromDb.setContentEN(news.getContentEN());
        }
        if (Objects.nonNull(news.getContentUA()) && !"".equalsIgnoreCase(news.getContentUA())) {
            newsFromDb.setContentUA(news.getContentUA());
        }
        if (Objects.nonNull(news.getShortDescriptionEN()) && !"".equalsIgnoreCase(news.getShortDescriptionEN())) {
            newsFromDb.setShortDescriptionEN(news.getShortDescriptionEN());
        }
        if (Objects.nonNull(news.getShortDescriptionUA()) && !"".equalsIgnoreCase(news.getShortDescriptionUA())) {
            newsFromDb.setShortDescriptionUA(news.getShortDescriptionUA());
        }
        if (Objects.nonNull(news.getImgEN()) && !"".equalsIgnoreCase(news.getImgEN())) {
            newsFromDb.setImgEN(news.getImgEN());
        }
        if (Objects.nonNull(news.getImgUA()) && !"".equalsIgnoreCase(news.getImgUA())) {
            newsFromDb.setImgUA(news.getImgUA());
        }
        if (!Objects.nonNull(newsFromDb.getCreationDate())) {
            newsFromDb.setCreationDate(Instant.now());
        }
        newsFromDb.setUpdateDate(Instant.now());

        return newsRepository.save(newsFromDb);
    }

}
