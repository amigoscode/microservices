package com.amigoscode.apimanagement.apikey;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Ali Bouali
 */
public interface ApiKeyRepository extends JpaRepository<ApiKey, Integer> {

  Optional<ApiKey> findByKey(String key);

  @Query(""
      + "SELECT "
      + "a "
      + "FROM ApiKey a "
      + "INNER JOIN Application ap "
      + "on a.id = ap.apiKey.id "
      + "WHERE a.key = :apiKey AND ap.name = :appName"
  )
  Optional<ApiKey> findByKeyAndApplicationName(String apiKey, String appName);
}
