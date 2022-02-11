package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ua.uesf.exception.NotFoundException;
import org.ua.uesf.model.GameDto;
import org.ua.uesf.service.game.GameService;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor

@RestController
public class GameController {

    private final GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<GameDto>> getGameByPage(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        List<GameDto> newsPage = gameService.getAllGames(page, size);
        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @GetMapping("/game/{id}")
    public GameDto getGameById(@PathVariable("id") long id) {
        return gameService.findGameById(id);
    }

    @PostMapping("/game")
    public ResponseEntity<Void> saveGame(@RequestBody GameDto gameDto) {
        gameService.saveGame(gameDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/game/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }

    @PutMapping("/game/{id}")
    public void updateNews(@RequestBody GameDto gameDto,
                           @PathVariable("id") Long id) {
        if (!Objects.equals(gameDto.getId(), id))
            throw new NotFoundException("The id's do not match " + gameDto.getId() + " " + id);

        gameService.updateGame(id, gameDto);
    }

}
