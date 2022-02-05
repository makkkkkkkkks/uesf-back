package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.News;
import org.ua.uesf.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
