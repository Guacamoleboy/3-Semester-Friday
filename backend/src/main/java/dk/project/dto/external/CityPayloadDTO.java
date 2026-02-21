package dk.project.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityPayloadDTO {

    // Attributes
    private List<CityDTO> results;
    @JsonProperty("generationtime_ms")
    private Double generationTimeMs;

}