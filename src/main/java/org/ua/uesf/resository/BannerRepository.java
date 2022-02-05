package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ua.uesf.model.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

}


