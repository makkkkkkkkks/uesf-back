package org.ua.uesf.service.game.impl;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.messages.AlreadyExistException;
import org.ua.uesf.exception.messages.NotFoundException;
import org.ua.uesf.mapper.GameMapper;
import org.ua.uesf.model.Game;
import org.ua.uesf.model.GameDto;
import org.ua.uesf.resository.GameRepository;
import org.ua.uesf.service.game.GameService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    @Override
    public List<GameDto> getAllGames(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Game> game = gameRepository.findAll(paging);

        return game.getContent()
                .stream()
                .map(gameMapper::gameToTdo)
                .collect(Collectors.toList());
    }

    @Override
    public void saveGame(GameDto gameDto) {
        if (gameDto.getId() != null && gameRepository.findById(gameDto.getId()).isPresent()) {
            throw new AlreadyExistException("Game with id " + gameDto.getId() + "already exist");
        }
        gameRepository.save(gameMapper.gameDtoToGame(gameDto));
    }

    @Override
    public void deleteGameById(Long id) {
        if (!gameRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find game with id: " + id);
        }
        gameRepository.deleteById(id);
    }

    @Override
    public GameDto findGameById(Long id) {
        if (!gameRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find game with id: " + id);
        }
        Game game = gameRepository.findById(id).get();
        return gameMapper.gameToTdo(game);
    }

    @Override
    public Game updateGame(Long id, GameDto gameDto) {
        if (!gameRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find game with id:" + id);
        }
        Game gameFromDb = gameRepository.findById(id).get();


        if (Objects.nonNull(gameDto.getImg()) && !"".equalsIgnoreCase(gameDto.getImg())) {
            gameFromDb.setImg(gameDto.getImg());
        }
        if (Objects.nonNull(gameDto.getTitle()) && !"".equalsIgnoreCase(gameDto.getTitle())) {
            gameFromDb.setTitle(gameDto.getTitle());
        }

        return gameRepository.save(gameFromDb);
    }
}
