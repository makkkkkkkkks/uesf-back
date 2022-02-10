package org.ua.uesf.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.ua.uesf.model.Game;
import org.ua.uesf.model.News;
import org.ua.uesf.model.NewsStatus;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(builder = @Builder(disableBuilder = true))
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titleUA", source = "newsDTO.titleUA")
    @Mapping(target = "titleEN", source = "newsDTO.titleEN")
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "contentUA", source = "newsDTO.contentUA")
    @Mapping(target = "contentEN", source = "newsDTO.contentEN")
    @Mapping(target = "shortDescriptionUA", source = "newsDTO.shortDescriptionUA")
    @Mapping(target = "shortDescriptionEN", source = "newsDTO.shortDescriptionEN")
    @Mapping(target = "newsStatus", expression = "java(mapToStatus(newsDTO))")
    @Mapping(target = "imgUA", source = "newsDTO.imgUA")
    @Mapping(target = "imgEN", source = "newsDTO.imgEN")
    News dtoToNews(NewsDTO newsDTO);

    @Mapping(target = "id", source = "news.id")
    @Mapping(target = "title", source = "news.titleUA")
    @Mapping(target = "game", expression = "java(mapGame(news))")
    @Mapping(target = "content", source = "news.contentUA")
    @Mapping(target = "shortDescription", source = "news.shortDescriptionUA")
    @Mapping(target = "newsStatus", expression = "java(statusToString(news))")
    @Mapping(target = "img", source = "news.imgUA")
    @Mapping(target = "creationDate", source = "news.creationDate")
    @Mapping(target = "updateDate", source = "news.updateDate")
    GeneralNewsDTO dtoUA(News news);

    @Mapping(target = "id", source = "news.id")
    @Mapping(target = "title", source = "news.titleEN")
    @Mapping(target = "game", expression = "java(mapGame(news))")
    @Mapping(target = "content", source = "news.contentEN")
    @Mapping(target = "shortDescription", source = "news.shortDescriptionEN")
    @Mapping(target = "newsStatus", expression = "java(statusToString(news))")
    @Mapping(target = "img", source = "news.imgEN")
    @Mapping(target = "creationDate", source = "news.creationDate")
    @Mapping(target = "updateDate", source = "news.updateDate")
    GeneralNewsDTO dtoEN(News news);


    default NewsStatus mapToStatus(NewsDTO newsDTO) {
        if ("PUBLISHED".equals(newsDTO.getNewsStatus())) return NewsStatus.PUBLISHED;
        return NewsStatus.NOT_PUBLISHED;
    }

    default String statusToString(News news) {
        if (news.getNewsStatus().toString().equals("PUBLISHED")) return "PUBLISHED";
        return "NOT_PUBLISHED";
    }

    default Set<String> mapGame(News news) {
        return news.getGame().stream()
                .map(Game::getId)
                .map(String::valueOf)
                .collect(Collectors.toSet());

    }
}
