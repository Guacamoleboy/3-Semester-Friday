package dk.project.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiagnoseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "diagnose_id": 0
    //          "diagnose_name": name
    //          "diagnose_description": description
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("diagnose_id")
    private int id;
    @JsonProperty("diagnose_name")
    private String name;
    @JsonProperty("diagnose_description")
    private String description;

}