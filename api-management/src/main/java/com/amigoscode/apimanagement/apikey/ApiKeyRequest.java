package com.amigoscode.apimanagement.apikey;

import java.util.List;
import lombok.Data;

/**
 * @author Ali Bouali
 */
@Data
public class ApiKeyRequest {

  private String name;

  private String description;

  private List<String> applications;
}
