package dk.project.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ApiRequestDTO {

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "api_name": name
    //          "key_id": keyId
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("api_name")
    private String name;
    @JsonProperty("key_id")
    private String keyId;

}