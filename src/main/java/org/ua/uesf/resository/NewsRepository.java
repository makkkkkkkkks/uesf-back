
package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

}


