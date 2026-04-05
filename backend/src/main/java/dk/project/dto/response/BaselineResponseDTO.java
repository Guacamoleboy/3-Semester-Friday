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
    //          "id": id
    //          "client_id": id
    //          "diagnose_id": id
    //          "created_at": timestamp
    //          "end_date": timestamp
    //          "last_updated": timestamp
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    @JsonProperty("id")
    private int id;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("diagnose_id")
    private int diagnoseId;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("end_date")
    private Timestamp endDate;
    @JsonProperty("last_updated")
    private Timestamp lastUpdated;

}