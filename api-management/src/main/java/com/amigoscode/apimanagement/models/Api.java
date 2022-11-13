package com.amigoscode.apimanagement.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ali Bouali
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Api {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true, nullable = false)
  private String key;

  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  private LocalDateTime createdDated;

  private LocalDateTime expirationDate;

  private boolean enabled;

  private boolean neverExpires;

  private boolean approved;

  private boolean revoked;

  @OneToMany(mappedBy = "api")
  private List<Application> applications;

}
