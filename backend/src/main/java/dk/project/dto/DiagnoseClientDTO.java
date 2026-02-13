package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class DiagnoseClientDTO {

    // Attributes
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
