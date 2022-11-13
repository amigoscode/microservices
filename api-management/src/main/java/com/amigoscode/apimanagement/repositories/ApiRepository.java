package com.amigoscode.apimanagement.repositories;

import com.amigoscode.apimanagement.models.Api;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Ali Bouali
 */
public interface ApiRepository extends JpaRepository<Api, Integer> {

  @Query("select a from Api a inner join Application ap on a.id = ap.api.id where a.key = :key and ap.name = :appName")
  Optional<Api> findByKeyAndApplicationName(String key, String appName);
  Optional<Api> findByKey(String key);

}
