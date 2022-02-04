package org.ua.uesf.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.ua.uesf.model.News;
import org.ua.uesf.model.NewsStatus;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;

@Mapper(builder = @Builder(disableBuilder = true))
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titleUA", source = "newsDTO.titleUA")
    @Mapping(target = "titleEN", source = "newsDTO.titleEN")
    @Mapping(target = "game", source = "newsDTO.game")
    @Mapping(target = "contentUA", source = "newsDTO.contentUA")
    @Mapping(target = "contentEN", source = "newsDTO.contentEN")
    @Mapping(target = "shortDescriptionUA", source = "newsDTO.shortDescriptionUA")
    @Mapping(target = "shortDescriptionEN", source = "newsDTO.shortDescriptionEN")
    @Mapping(target = "newsStatus", expression = "java(mapToStatus(newsDTO))")
  /*  @Mapping(target = "newsStatus", expression = "java(mapToStatus(newsDTO))")
    @Mapping(target = "newsStatus", expression = "java(mapToStatus(newsDTO))")*/


    @Mapping(target = "creationDate", source = "newsDTO.creationDate")
    @Mapping(target = "updateDate", source = "newsDTO.updateDate")
    News dtoToNews(NewsDTO newsDTO);


    @Mapping(target = "id", source = "news.id")
    @Mapping(target = "title", source = "news.titleUA")
    @Mapping(target = "game", source = "news.game")
    @Mapping(target = "content", source = "news.contentUA")
    @Mapping(target = "shortDescription", source = "news.shortDescriptionUA")
    @Mapping(target = "newsStatus", expression = "java(statusToString(news))")
    @Mapping(target = "creationDate", source = "news.creationDate")
    @Mapping(target = "updateDate", source = "news.updateDate")
    GeneralNewsDTO dtoUA(News news);

    @Mapping(target = "id", source = "news.id")
    @Mapping(target = "title", source = "news.titleEN")
    @Mapping(target = "game", source = "news.game")
    @Mapping(target = "content", source = "news.contentEN")
    @Mapping(target = "shortDescription", source = "news.shortDescriptionEN")
    @Mapping(target = "newsStatus", expression = "java(statusToString(news))")
    @Mapping(target = "creationDate", source = "news.creationDate")
    @Mapping(target = "updateDate", source = "news.updateDate")
    GeneralNewsDTO dtoEN(News news);


    default NewsStatus mapToStatus(NewsDTO newsDTO) {
        if ("PUBLISHED".equals(newsDTO.getNewsStatus())) return NewsStatus.PUBLISHED;
        if ("NOW_PUBLISHED".equals(newsDTO.getNewsStatus())) return NewsStatus.NOW_PUBLISHED;
        return NewsStatus.EDIT;
    }

    default String statusToString(News news) {
        if (news.getNewsStatus().equals("PUBLISHED")) return "PUBLISHED";
        if (news.getNewsStatus().equals("NOW_PUBLISHED")) return "NOW_PUBLISHED";
        return "EDIT";
    }
}
