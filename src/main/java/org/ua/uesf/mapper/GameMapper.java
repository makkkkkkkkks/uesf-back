package org.ua.uesf.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.ua.uesf.model.Game;
import org.ua.uesf.model.GameDto;

@Mapper(builder = @Builder(disableBuilder = true))
public interface GameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "img", source = "gameDto.img")
    @Mapping(target = "title", source = "gameDto.title")
    @Mapping(target = "news", ignore = true)
    Game gameDtoToGame(GameDto gameDto);

    @Mapping(target = "id", source = "game.id")
    @Mapping(target = "img", source = "game.img")
    @Mapping(target = "title", source = "game.title")
    @Mapping(target = "news", ignore = true)
    GameDto gameToTdo(Game game);
}
