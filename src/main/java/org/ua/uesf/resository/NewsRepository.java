
package org.ua.uesf.resository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ua.uesf.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {


    @Query(value = "SELECT * FROM NEWS WHERE news_status = ?1", nativeQuery = true)
    Page<News> findAll(String newsStatus, Pageable pageable);

    /*@Query(value = "SELECT * FROM NEWS WHERE news_status = ?1", nativeQuery = true)
    Page<News> findAll(String newsStatus, Pageable pageable);*/

}


