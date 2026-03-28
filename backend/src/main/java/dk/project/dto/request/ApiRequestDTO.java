package dk.project.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ApiRequestDTO {

    @JsonProperty("api_name")
    private String name;
    @JsonProperty("key_id")
    private String keyId;

}