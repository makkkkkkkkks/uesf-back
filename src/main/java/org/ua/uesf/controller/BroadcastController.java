package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ua.uesf.exception.messages.NotFoundException;

import org.ua.uesf.model.Broadcast;
import org.ua.uesf.service.broadcast.BroadcastService;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor

@RestController
public class BroadcastController {

    private final BroadcastService broadcastService;


    @GetMapping("/broadcast")
    public ResponseEntity<List<Broadcast>> getBroadcastByPage(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        List<Broadcast> newsPage = broadcastService.getAllBroadcast(page, size);
        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @GetMapping("/broadcast/{id}")
    public Broadcast getBroadcastById(@PathVariable("id") long id) {
        return broadcastService.findBroadcastById(id);
    }

    @PostMapping("/broadcast")
    public ResponseEntity<Void> saveBroadcast(@RequestBody Broadcast broadcast) {
        broadcastService.saveBroadcast(broadcast);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/broadcast/{id}")
    public void deleteBroadcast(@PathVariable Long id) {
        broadcastService.deleteBroadcastById(id);
    }

    @PutMapping("/broadcast/{id}")
    public void updateBroadcast(@RequestBody Broadcast broadcast,
                                @PathVariable("id") Long id) {
        if (!Objects.equals(broadcast.getId(), id))
            throw new NotFoundException("The id's do not match " + broadcast.getId() + " " + id);

        broadcastService.updateBroadcast(id, broadcast);
    }

}
