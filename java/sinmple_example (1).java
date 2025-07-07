import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            String webhookId = "YOUR_WEBHOOK_ID";
            String webhookCode = "YOUR_WEBHOOK_CODE";
            String urlStr = "https://psyll.com/env/webhook/" + webhookId;

            Map<String, String> payload = new HashMap<>();
            payload.put("code", webhookCode);
            payload.put("action", "BUY");
            payload.put("quantity", "0.0034");
            payload.put("symbol", "BTCUSDT");

            String formData = payload.entrySet().stream()
                .map(e -> {
                    try {
                        return URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8.toString()) + "=" +
                               URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8.toString());
                    } catch (Exception ex) {
                        return "";
                    }
                })
                .collect(Collectors.joining("&"));

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            try (var outputStream = conn.getOutputStream()) {
                outputStream.write(formData.getBytes(StandardCharsets.UTF_8));
            }

            int statusCode = conn.getResponseCode();
            System.out.println("Status code: " + statusCode);

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            System.out.println("Response:");
            System.out.println(response.toString());

            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}