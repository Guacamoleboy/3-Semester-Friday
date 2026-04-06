package dk.project.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestDTO {


    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "username": username
    //          "password": password
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________



    // _________________________________________________________________________________________________________________
    // JSON Fields

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}