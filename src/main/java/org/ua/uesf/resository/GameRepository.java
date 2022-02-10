package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
