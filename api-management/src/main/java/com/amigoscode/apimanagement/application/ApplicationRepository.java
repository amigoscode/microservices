package com.amigoscode.apimanagement.application;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Bouali
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

  Optional<Application> findByName(String appName);
}
