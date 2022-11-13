package com.amigoscode.apimanagement.dto;

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
public class AssignApplicationRequest {

  private String apiKey;

  private String appName;
}
