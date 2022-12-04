package com.amigoscode.apimanagement.apikey;

import java.util.Optional;

import com.amigoscode.clients.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Integer> {

  @Query("""
    SELECT a
    FROM ApiKey a
    INNER JOIN Application ap
    ON a.id = ap.apiKey.id
    WHERE a.key = :key
    AND ap.name = :appName
  """)
  Optional<ApiKey> findByKeyAndApplicationName(String key, Application appName);

  @Query("""
    SELECT
    CASE WHEN COUNT(k) > 0
      THEN TRUE
      ELSE FALSE
    END
    FROM ApiKey k
    WHERE k.key = :key
  """)
  boolean doesKeyExists(String key);

  @Query("SELECT k FROM ApiKey k WHERE k.key = :key")
  Optional<ApiKey> findByKey(String key);

}
