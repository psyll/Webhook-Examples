use reqwest::Client;                // For sending HTTP requests
use serde_json::Value;             // For handling JSON responses
use std::collections::HashMap;     // For building the POST payload

#[tokio::main]                     // Marks the async main function using the Tokio runtime
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Webhook ID and code (replace with actual values)
    let webhook_id = "YOUR_WEBHOOK_ID";
    let webhook_code = "YOUR_WEBHOOK_CODE";

    // Construct the full webhook URL using the webhook ID
    let url = format!("https://psyll.com/env/webhook/{}", webhook_id);

    // Prepare the payload to send as form data
    let mut payload = HashMap::new();
    payload.insert("code", webhook_code);       // Authentication code
    payload.insert("action", "BUY");            // Action to perform
    payload.insert("quantity", "0.0034");       // Amount to trade
    payload.insert("symbol", "BTCUSDT");        // Trading pair symbol

    // Create a new HTTP client
    let client = Client::new();

    // Send the POST request with the form data
    let response = client.post(&url)
        .form(&payload)
        .send()
        .await?;  // Await the response asynchronously

    // Print the HTTP status code
    println!("Status code: {}", response.status());

    // Parse the response body as JSON
    let json_response: Value = response.json().await?;

    // Pretty-print the parsed JSON response
    println!("Response:");
    println!("{}", serde_json::to_string_pretty(&json_response)?);

    Ok(())  // Return success
}
