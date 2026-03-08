package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import dk.project.dto.UserDTO;

@Data
public class AuthResponseDTO {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("user")
    private UserDTO user;

}