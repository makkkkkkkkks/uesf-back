
package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ua.uesf.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}


