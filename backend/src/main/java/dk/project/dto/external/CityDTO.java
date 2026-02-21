package dk.project.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDTO {

    // Attributes
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer population;
    @JsonProperty("postcodes")
    private List<String> postcodes;                                             // 1 entry - multiple returns
    @JsonProperty("admin2_id")
    private Integer admin2Id;
    private String admin2;

}