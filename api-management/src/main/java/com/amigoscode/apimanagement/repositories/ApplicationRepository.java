package com.amigoscode.apimanagement.repositories;

import com.amigoscode.apimanagement.models.Application;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ali Bouali
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

  Optional<Application> findByName(String name);

}
