package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties
public class BaselineResponseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": 1
    //          "created_at": date
    //          "end_date": date
    //          "last_updated": date
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    @JsonProperty("id")
    private int id;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("end_date")
    private Timestamp endDate;
    @JsonProperty("last_updated")
    private Timestamp lastUpdated;

}