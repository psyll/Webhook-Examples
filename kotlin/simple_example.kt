import okhttp3.FormBody // Provides a builder for URL-encoded form data
import okhttp3.OkHttpClient // Provides HTTP client functionality
import okhttp3.Request // Represents an HTTP request
import java.io.IOException // Exception for I/O-related errors

// Entry point of the application
fun main() {
    // Define webhook configuration
    val webhookId = "YOUR_WEBHOOK_ID" // Unique identifier for the webhook
    val webhookCode = "YOUR_WEBHOOK_CODE" // Authentication code for the webhook
    val url = "https://psyll.com/env/webhook/$webhookId" // Construct the webhook URL using string interpolation

    // Initialize OkHttpClient for making HTTP requests
    val client = OkHttpClient()

    // Build form-encoded payload for the webhook request
    val formBody = FormBody.Builder()
        .add("code", webhookCode) // Add webhook authentication code
        .add("action", "BUY") // Specify the action to perform
        .add("quantity", "0.0034") // Set quantity for the transaction
        .add("symbol", "BTCUSDT") // Set trading pair symbol
        .build()

    // Build the HTTP POST request with the URL and form data
    val request = Request.Builder()
        .url(url) // Set the target URL
        .post(formBody) // Set the request method to POST with form data
        .build()

    try {
        // Execute the request and handle the response using a try-with-resources pattern
        client.newCall(request).execute().use { response ->
            // Output the HTTP status code
            println("Status code: ${response.code}")
            // Read and output the response body
            val responseBody = response.body?.string()
            println("Response:")
            println(responseBody)
        }
    } catch (e: IOException) {
        // Handle and log any I/O-related errors that occur during the request
        println("Error: ${e.message}")
    }
}