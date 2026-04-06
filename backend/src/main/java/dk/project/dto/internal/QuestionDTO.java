package dk.project.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "question_id": 0
    //          "question_title": title
    //          "question_description": description
    //          "question_value": 0
    //          "question_diagnosis_id": 0
    //          "question_medication_id": 0
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("question_id")
    private int id;
    @JsonProperty("question_title")
    private String title;
    @JsonProperty("question_description")
    private String description;
    @JsonProperty("question_value")
    private byte value;
    @JsonProperty("question_diagnosis_id")
    private Integer diagnosisId;
    @JsonProperty("question_medication_id")
    private Integer medicationId;

}