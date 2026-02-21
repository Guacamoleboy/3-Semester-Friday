package dk.project.service.external;

import dk.project.dto.external.WeatherDTO;
import dk.project.exception.ApiException;
import dk.project.config.PoolConfig;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class WeatherService {

    // Attributes
    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast?current_weather=true";

    // ___________________________________________________________________

    public CompletableFuture<WeatherDTO> getWeatherByCoordinates(Double latitude, Double longitude){

        // Initial Check
        if (latitude == null || longitude == null) {
            return CompletableFuture.failedFuture(
                    new ApiException("Latitude or Longitude can't be null", this.getClass().getName())
            );
        }

        // Might need to set language on "," "." here. Not sure yet.
        String finalUrl = String.format("%s&latitude=%s&longitude=%s", BASE_URL, latitude, longitude);

        try {

            // Request Setup
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalUrl))
                    .GET()
                    .build();

            // Async Request
            return PoolConfig.getClient()
                    .sendAsync(request, BodyHandlers.ofString())
                    .thenApply(response -> {

                        // 200 validation
                        if (response.statusCode() != 200) {
                            throw new ApiException(
                                    response.statusCode(),
                                    "Failed to fetch weather for coordinates: " + latitude + ", " + longitude,
                                    this.getClass().getName()
                            );
                        }

                        // JSON -> Java Object
                        try {
                            return PoolConfig.getMapper().readValue(response.body(), WeatherDTO.class);
                        } catch (Exception e) {
                            throw new ApiException(
                                    "Failed to parse WeatherDTO",
                                    e,
                                    this.getClass().getName()
                            );
                        }

                    });

        } catch (Exception e) {
            return CompletableFuture.failedFuture(
                    new ApiException("Failed to build weather request", e, this.getClass().getName())
            );
        }

    }

}