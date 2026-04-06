package dk.project.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class DiagnoseClientDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "diagnose_client_id": 0
    //          "diagnose_client_diagnose_id": 0
    //          "diagnose_client_client_id": clientId
    //          "diagnose_client_created_at": timestamp
    //          "diagnose_client_last_updated": timestamp
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("diagnose_client_id")
    private int id;
    @JsonProperty("diagnose_client_diagnose_id")
    private Integer diagnoseId;
    @JsonProperty("diagnose_client_client_id")
    private String clientId;
    @JsonProperty("diagnose_client_created_at")
    private Timestamp createdAt;
    @JsonProperty("diagnose_client_last_updated")
    private Timestamp lastUpdated;

}