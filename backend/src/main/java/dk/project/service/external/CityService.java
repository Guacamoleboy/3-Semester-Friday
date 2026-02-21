package dk.project.service.external;

import dk.project.dto.external.CityPayloadDTO;
import dk.project.exception.ApiException;
import dk.project.config.PoolConfig;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CityService {

    // Attributes
    private static final String BASE_URL = "https://geocoding-api.open-meteo.com/v1/search?name=";
    private static final String ALLOWED_AA[] = {
            "Aabenraa",
            "Aalborg",
            "Aarhus",
            "Aakirkeby",
            "Aabybro",
            "Aars"
    };
    private static final String ALLOWED_AE[] = {/* None Found */};
    private static final String ALLOWED_OE[] = {/* None Found */};
    private static final String COUNTRY_CODE = "DK";

    // Using HashSet to prevent long load times from ArrayList where
    // each element would be checked untill query found.
    // With HashSet we can just search for the query hash.
    // This means it doesn't matter if we have 10.000 cities or 1 million. Same time.
    // Where ArrayList would have to query all cities untill correct is found. (slow)
    private static final Set<String> ALLOWED_CITIES = new HashSet<>();

    // Prevents Methods from having to do calc each time. Does it once on Class Load and stores
    // in HashSet after. No access modifier as it's not a method but a class initializer for
    // static methods and attributes. Stream.of() does the same and could be used.
    // Doing this method for clarity.
    static {
        for (String s : ALLOWED_AA) ALLOWED_CITIES.add(s.toLowerCase());
        for (String s : ALLOWED_AE) ALLOWED_CITIES.add(s.toLowerCase());
        for (String s : ALLOWED_OE) ALLOWED_CITIES.add(s.toLowerCase());
    }

    // _____________________________________________________________________________

    public CompletableFuture<CityPayloadDTO> getCityByName(String cityName) {

        // Initial Check
        if (cityName == null){
            return CompletableFuture.failedFuture(
                    new ApiException("cityName can't be null", this.getClass().getName()
            ));
        }

        // Check if query is allowed or should be replaced with "å", "æ" or "ø"
        boolean isAllowed = ALLOWED_CITIES.contains(cityName.toLowerCase());
        String fixedName = isAllowed ? cityName.toLowerCase() : cityName.toLowerCase()
                .replace("aa", "å")
                .replace("ae", "æ")
                .replace("oe", "ø");

        // Fixed Encoded URL due to "æ", "ø", "å" in English API.
        String encodedName = URLEncoder.encode(fixedName, StandardCharsets.UTF_8);
        String finalUrl = BASE_URL + encodedName + "&country=" + COUNTRY_CODE;

        // Async call
        try {

            // Request setup
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalUrl))
                    .GET()
                    .build();

            // Return CompletableFuture<T>
            return PoolConfig.getClient()
                    .sendAsync(request, BodyHandlers.ofString())
                    .thenApply(response -> {

                        // Validation
                        if (response.statusCode() != 200) {
                            throw new ApiException(response.statusCode(), "Failed to fetch city " + cityName, this.getClass().getName());
                        }

                        // JSON -> Java Object of CityPayloadDTO
                        try {
                            return PoolConfig.getMapper().readValue(response.body(), CityPayloadDTO.class);
                        } catch (Exception e) {
                            throw new ApiException("Failed to parse CityPayloadDTO", e, this.getClass().getName());
                        }

                    });
        } catch (Exception e) {
            // Must return a CompletableFuture as per definition. Returns if request failed (pre-async)
            return CompletableFuture.failedFuture(new ApiException(
                    "Failed to build request",
                    e,
                    this.getClass().getName()));
        }

    }

}
