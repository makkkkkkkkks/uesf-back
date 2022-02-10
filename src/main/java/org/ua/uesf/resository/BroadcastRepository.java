package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.Broadcast;

public interface BroadcastRepository extends JpaRepository<Broadcast, Long> {

}
