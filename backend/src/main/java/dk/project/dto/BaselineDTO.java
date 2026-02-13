package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class BaselineDTO {

    // Attributes
    @JsonProperty("baseline_id")
    private int id;

    @JsonProperty("baseline_client_id")
    private String clientId;

    @JsonProperty("baseline_diagnose_id")
    private Integer diagnoseId;

    @JsonProperty("baseline_created_at")
    private Timestamp createdAt;

    @JsonProperty("baseline_end_date")
    private Timestamp endDate;

    @JsonProperty("baseline_last_updated")
    private Timestamp lastUpdated;

    @JsonProperty("baseline_individual_ids")
    private List<Integer> baselineIndividualIds;

}


