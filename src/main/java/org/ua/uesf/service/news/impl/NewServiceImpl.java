package org.ua.uesf.service.news.impl;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.NotFoundException;
import org.ua.uesf.mapper.NewsMapper;
import org.ua.uesf.model.News;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;
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
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    public List<GeneralNewsDTO> findNews(String locale, Integer page, Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<News> news = newsRepository.findAll(paging);
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
        newsRepository.save(newsMapper.dtoToNews(newsDTO));
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
            newsFromDb.setContentEN(news.getTitleUA());
        }
        if (Objects.nonNull(news.getTitleEN()) && !"".equalsIgnoreCase(news.getTitleEN())) {
            newsFromDb.setContentEN(news.getTitleEN());
        }

        if (Objects.nonNull(news.getGame()) && !"".equalsIgnoreCase(news.getGame())) {
            newsFromDb.setContentEN(news.getGame());
        }
        if (Objects.nonNull(news.getContentEN()) && !"".equalsIgnoreCase(news.getContentEN())) {
            newsFromDb.setContentEN(news.getContentEN());
        }
        if (Objects.nonNull(news.getContentUA()) && !"".equalsIgnoreCase(news.getContentUA())) {
            newsFromDb.setContentEN(news.getContentUA());
        }
        if (Objects.nonNull(news.getShortDescriptionEN()) && !"".equalsIgnoreCase(news.getShortDescriptionEN())) {
            newsFromDb.setContentEN(news.getShortDescriptionEN());
        }
        if (Objects.nonNull(news.getShortDescriptionUA()) && !"".equalsIgnoreCase(news.getShortDescriptionUA())) {
            newsFromDb.setContentEN(news.getShortDescriptionUA());
        }
        if (Objects.nonNull(news.getImgEN()) && !"".equalsIgnoreCase(news.getImgEN())) {
            newsFromDb.setContentEN(news.getImgEN());
        }
        if (Objects.nonNull(news.getImgUA()) && !"".equalsIgnoreCase(news.getImgUA())) {
            newsFromDb.setContentEN(news.getImgUA());
        }
        return newsRepository.save(news);
    }

}
