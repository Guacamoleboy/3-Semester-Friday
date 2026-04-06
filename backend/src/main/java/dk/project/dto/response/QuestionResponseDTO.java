package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class QuestionResponseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": 1
    //          "question_title": text
    //          "question_description": text here
    //          "value": 6
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________

    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("id")
    private int id;
    @JsonProperty("question_title")
    private String questionTitle;
    @JsonProperty("question_description")
    private String questionDescription;
    @JsonProperty("value")
    private byte questionValue;

}