package org.ua.uesf.service.news;

import org.ua.uesf.model.News;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    List<GeneralNewsDTO> findNews(String locale, Integer page, Integer size);

    List<GeneralNewsDTO> findNewsByGameId(String locale, Integer page, Integer size, Long Id);

    void saveNews(NewsDTO newsDTO);

    void deleteNewsById(Long id);

    News findById(Long id);

    News update(Long id, News news);


}
