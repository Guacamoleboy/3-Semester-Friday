package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;

@Data
@JsonIgnoreProperties
public class ClientResponseDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON Output
    // ____________________
    //
    //      {
    //          "id": 1
    //          "id_ending": id
    //          "created_at": timestamp
    //          "last_login": timestamp
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("id")
    private String id;
    @JsonProperty("id_ending")
    private Integer idEnding;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("last_login")
    private Timestamp lastLogin;

}