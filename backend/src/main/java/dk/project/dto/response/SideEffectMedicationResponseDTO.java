package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class SideEffectMedicationResponseDTO {

    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": 1,
    //          "name": name
    //          "description": text here
    //          "header": 3
    //          "medication_category_id": id
    //          "medication_id": id
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
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("header")
    private int header;
    @JsonProperty("medication_category_id")
    private int medicationCategoryId;
    @JsonProperty("medication_id")
    private int medicationId;

}