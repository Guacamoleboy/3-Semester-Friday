package dk.project.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherDTO {

    // _________________________________________________________________________________________________________________

    // Expected JSON
    // _____________
    //
    //      {
    //          "timezone": timezone
    //          "current": {
    //              "temperature_2m": 0.0
    //              "wind_speed_10m": 0.0
    //          }
    //          "current_units": {
    //              "temperature_2m": unit
    //              "wind_speed_10m": unit
    //          }
    //      }
    //
    // ____________________
    // Tested: NO
    // Last Tested: N/A

    // _________________________________________________________________________________________________________________


    // _________________________________________________________________________________________________________________
    // JSON Fields

    private String timezone;
    private CurrentDTO current;
    @JsonProperty("current_units")
    private CurrentUnitsDTO currentUnits;

    // _________________________________________________________________________________________________________________
    // Nested class for "current"

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @Builder
    public static class CurrentDTO {
        @JsonProperty("temperature_2m")
        private Double temperature;
        @JsonProperty("wind_speed_10m")
        private Double windSpeed;
    }

    // _________________________________________________________________________________________________________________
    // Nested class for "current_units"

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @Builder
    public static class CurrentUnitsDTO {
        @JsonProperty("temperature_2m")
        private String temperatureUnit;
        @JsonProperty("wind_speed_10m")
        private String windSpeedUnit;
    }

    // _________________________________________________________________________________________________________________

    public String getWithUnit(String searchType) {

        // Initial
        if (current == null || currentUnits == null) return null;

        // Switch-case over query
        switch (searchType.toLowerCase()) {
            case "temp":
            case "temperatur":
            case "temperature":
                if (current.getTemperature() != null && currentUnits.getTemperatureUnit() != null) {
                    return current.getTemperature() + currentUnits.getTemperatureUnit();
                }
                break;
            case "wind":
            case "vind":
            case "vindhastighed":
            case "vind hastighed":
            case "windspeed":
            case "wind_speed":
                if (current.getWindSpeed() != null && currentUnits.getWindSpeedUnit() != null) {
                    return current.getWindSpeed() + " " + currentUnits.getWindSpeedUnit();
                }
                break;
            default:
                throw new IllegalArgumentException("Ikke godkendt query format: " + searchType);
        }
        return null;
    }

}