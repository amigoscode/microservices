package com.amigoscode.apimanagement.application;

import javax.persistence.*;

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

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private com.amigoscode.clients.Application name;

  private boolean enabled;

  private boolean approved;

  private boolean revoked;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
          name = "api_key_id",
          referencedColumnName = "id"
  )
  private ApiKey apiKey;
}
