package org.ua.uesf.service.broadcast;

import org.ua.uesf.model.Broadcast;

import java.util.List;

public interface BroadcastService {

    List<Broadcast> getAllBroadcast(Integer page, Integer size);

    void saveBroadcast(Broadcast broadcast);

    void deleteBroadcastById(Long id);

    Broadcast findBroadcastById(Long id);

    void updateBroadcast(Long id, Broadcast broadcast);

}
