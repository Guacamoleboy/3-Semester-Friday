package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;

@Data
@JsonIgnoreProperties
public class DiagnoseClientResponseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": 1
    //          "diagnose_id": id
    //          "client_id": text
    //          "created_at": timestamp
    //          "last_updated": timestamp
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    @JsonProperty("id")
    private int id;
    @JsonProperty("diagnose_id")
    private int diagnoseId;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("last_updated")
    private Timestamp lastUpdated;

}