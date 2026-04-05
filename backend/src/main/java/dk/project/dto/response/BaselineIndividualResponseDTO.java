package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties
public class BaselineIndividualResponseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": id
    //          "baseline_id": id
    //          "question_id": id
    //          "value": byte
    //          "note": text here
    //          "created_at": localdate
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    @JsonProperty("id")
    private int id;
    @JsonProperty("baseline_id")
    private int baselineId;
    @JsonProperty("question_id")
    private int questionId;
    @JsonProperty("value")
    private byte value;
    @JsonProperty("note")
    private String note;
    @JsonProperty("created_at")
    private LocalDate createdAt;

}