
package org.ua.uesf.resository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ua.uesf.model.News;
import org.ua.uesf.model.NewsStatus;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT * FROM NEWS WHERE news_status = ?1", nativeQuery = true)
    Page<News> findAll(String newsStatus, Pageable pageable);

    @Query(value = "SELECT u from News u join u.game r where r.id=:id AND u.newsStatus=:newsStatus")
    Page<News> findAllByGame(Long id, NewsStatus newsStatus, Pageable pageable);

}


