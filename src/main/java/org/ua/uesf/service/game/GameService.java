package org.ua.uesf.service.game;

import org.ua.uesf.model.Game;
import org.ua.uesf.model.GameDto;

import java.util.List;

public interface GameService {

    List<GameDto> getAllGames(Integer page, Integer size);

    void saveGame(GameDto gameDto) ;

    void deleteGameById(Long id);

    GameDto findGameById(Long id);

    Game updateGame(Long id, GameDto gameDto);
}
