package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MedicationClientDTO {

    // Attributes
    @JsonProperty("medication_client_id")
    private int id;

    @JsonProperty("medication_client_medication_id")
    private Integer medicationId;

    @JsonProperty("medication_client_client_id")
    private String clientId;

    @JsonProperty("medication_client_amount")
    private int amount;

    @JsonProperty("medication_client_timeline")
    private String timeline;

    @JsonProperty("medication_client_created_at")
    private Timestamp createdAt;

    @JsonProperty("medication_client_last_updated")
    private Timestamp lastUpdated;

}