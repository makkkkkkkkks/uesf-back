package org.ua.uesf.service.broadcast.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.AlreadyExistException;
import org.ua.uesf.exception.NotFoundException;
import org.ua.uesf.model.Broadcast;
import org.ua.uesf.resository.BroadcastRepository;
import org.ua.uesf.service.broadcast.BroadcastService;

import java.util.List;
import java.util.Objects;


@AllArgsConstructor

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastRepository broadcastRepository;

    @Override
    public List<Broadcast> getAllBroadcast(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Broadcast> broadcasts = broadcastRepository.findAll(paging);
        return broadcasts.getContent();
    }

    @Override
    public void saveBroadcast(Broadcast broadcast) {
        if (broadcast.getId() != null && broadcastRepository.findById(broadcast.getId()).isPresent()) {
            throw new AlreadyExistException("Broadcast with id " + broadcast.getId() + "already exist");
        }
        broadcastRepository.save(broadcast);
    }

    @Override
    public void deleteBroadcastById(Long id) {
        if (!broadcastRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find broadcast with id: " + id);
        }
        broadcastRepository.deleteById(id);
    }

    @Override
    public Broadcast findBroadcastById(Long id) {
        if (!broadcastRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find broadcast with id: " + id);
        }
        return broadcastRepository.findById(id).get();
    }

    @Override
    public void updateBroadcast(Long id, Broadcast broadcast) {
        if (!broadcastRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find broadcast with id:" + id);
        }
        Broadcast broadcastFromDb = broadcastRepository.findById(id).get();

        if (Objects.nonNull(broadcast.getBroadcastOrdinal()) && !"".equalsIgnoreCase(broadcast.getBroadcastOrdinal())) {
            broadcastFromDb.setBroadcastOrdinal(broadcast.getBroadcastOrdinal());
        }
        if (Objects.nonNull(broadcast.getTWITCH_USERNAME()) && !"".equalsIgnoreCase(broadcast.getTWITCH_USERNAME())) {
            broadcastFromDb.setBroadcastOrdinal(broadcast.getTWITCH_USERNAME());
        }
        broadcastRepository.save(broadcastFromDb);
    }
}
