package dk.project.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "id": id
    //          "name": name
    //          "latitude": 0.0
    //          "longitude": 0.0
    //          "population": 0
    //          "postcodes": []
    //          "admin2_id": 0
    //          "admin2": text
    //      }
    //
    // ____________________
    // Tested: YES
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer population;
    @JsonProperty("postcodes")
    private List<String> postcodes;
    @JsonProperty("admin2_id")
    private Integer admin2Id;
    private String admin2;

}