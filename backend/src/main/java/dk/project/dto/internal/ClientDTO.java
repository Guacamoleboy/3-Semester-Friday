package dk.project.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ClientDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "client_id": clientId
    //          "client_id_ending": 0
    //          "client_created_at": timestamp
    //          "client_last_login": timestamp
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("client_id")
    private String id;
    @JsonProperty("client_id_ending")
    private int idEnding;
    @JsonProperty("client_created_at")
    private Timestamp createdAt;
    @JsonProperty("client_last_login")
    private Timestamp lastLogin;

}