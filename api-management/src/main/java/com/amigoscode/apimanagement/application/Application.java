package com.amigoscode.apimanagement.application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.amigoscode.apimanagement.apikey.ApiKey;
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
public class Application {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false, unique = true)
  private String name;

  private boolean enabled;

  private boolean approved;

  private boolean revoked;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private ApiKey apiKey;
}
