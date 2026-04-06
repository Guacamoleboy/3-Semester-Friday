package dk.project.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class BaselineDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "baseline_id": 0
    //          "baseline_client_id": clientId
    //          "baseline_diagnose_id": 0
    //          "baseline_created_at": timestamp
    //          "baseline_end_date": timestamp
    //          "baseline_last_updated": timestamp
    //          "baseline_individual_ids": []
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

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