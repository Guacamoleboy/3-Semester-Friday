package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;

@Data
@JsonIgnoreProperties
public class MedicationClientResponseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": 1
    //          "medication_id": 1
    //          "client_id": id
    //          "amount": 1
    //          "timeline": text
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
    @JsonProperty("medication_id")
    private int medicationId;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("timeline")
    private String timeline;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("last_updated")
    private Timestamp lastUpdated;

}