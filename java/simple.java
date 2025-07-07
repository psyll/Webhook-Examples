import java.io.BufferedReader; // Provides buffered reading of character input streams
import java.io.InputStreamReader; // Converts byte streams to character streams
import java.net.HttpURLConnection; // Supports HTTP-specific connection features
import java.net.URL; // Represents a Uniform Resource Locator
import java.net.URLEncoder; // Encodes strings for URL compatibility
import java.nio.charset.StandardCharsets; // Defines standard character encodings
import java.util.HashMap; // Provides a hash table data structure
import java.util.Map; // Interface for key-value pair collections
import java.util.stream.Collectors; // Provides stream operations for collecting results

public class Main {
    // Entry point of the application
    public static void main(String[] args) {
        try {
            // Define webhook configuration
            String webhookId = "YOUR_WEBHOOK_ID"; // Unique identifier for the webhook
            String webhookCode = "YOUR_WEBHOOK_CODE"; // Authentication code for the webhook
            String urlStr = "https://psyll.com/env/webhook/" + webhookId; // Construct the webhook URL

            // Create a map to hold form-encoded payload data
            Map<String, String> payload = new HashMap<>();
            payload.put("code", webhookCode); // Webhook authentication code
            payload.put("action", "BUY"); // Specify the action to perform
            payload.put("quantity", "0.0034"); // Quantity for the transaction
            payload.put("symbol", "BTCUSDT"); // Trading pair symbol

            // Encode payload as URL-encoded form data
            String formData = payload.entrySet().stream()
                .map(e -> {
                    try {
                        // Encode key and value for URL compatibility
                        return URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8.toString()) + "=" +
                               URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8.toString());
                    } catch (Exception ex) {
                        return ""; // Return empty string on encoding error
                    }
                })
                .collect(Collectors.joining("&")); // Join key-value pairs with '&'

            // Create URL object and open HTTP connection
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST"); // Set request method to POST
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // Set content type
            conn.setDoOutput(true); // Enable output for sending data

            // Write form data to the request body
            try (var outputStream = conn.getOutputStream()) {
                outputStream.write(formData.getBytes(StandardCharsets.UTF_8));
            }

            // Retrieve and print the HTTP status code
            int statusCode = conn.getResponseCode();
            System.out.println("Status code: " + statusCode);

            // Read and build the response from the server
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                // Read each line of the response until none remain
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Output the response content
            System.out.println("Response:");
            System.out.println(response.toString());

            // Close the connection
            conn.disconnect();
        } catch (Exception e) {
            // Handle and log any exceptions that occur during execution
            System.err.println("Error: " + e.getMessage());
        }
    }
}