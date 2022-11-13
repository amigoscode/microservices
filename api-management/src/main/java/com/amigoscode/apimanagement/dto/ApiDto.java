package com.amigoscode.apimanagement.dto;

import com.amigoscode.apimanagement.models.Api;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.time.LocalDateTime;
import java.util.List;
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
public class ApiDto {

  @JsonProperty(access = Access.READ_ONLY)
  private String key;

  private String name;

  private String description;

  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime createdDated;

  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime expirationDate;

  @JsonProperty(access = Access.READ_ONLY)
  private boolean enabled;

  @JsonProperty(access = Access.READ_ONLY)
  private boolean neverExpires;

  @JsonProperty(access = Access.READ_ONLY)
  private boolean approved;

  @JsonProperty(access = Access.READ_ONLY)
  private boolean revoked;

  private List<String> applications;

  public static Api toEntity(ApiDto dto) {
    return Api.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .build();
  }

}
