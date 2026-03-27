package dk.project.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiRequestDTO {

    @JsonProperty("name")
    private String name;

}