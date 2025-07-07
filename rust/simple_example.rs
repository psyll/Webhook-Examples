use reqwest::Client;
use serde_json::Value;
use std::collections::HashMap;

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let webhook_id = "YOUR_WEBHOOK_ID";
    let webhook_code = "YOUR_WEBHOOK_CODE";
    let url = format!("https://psyll.com/env/webhook/{}", webhook_id);

    let mut payload = HashMap::new();
    payload.insert("code", webhook_code);
    payload.insert("action", "BUY");
    payload.insert("quantity", "0.0034");
    payload.insert("symbol", "BTCUSDT");

    let client = Client::new();
    let response = client.post(&url)
        .form(&payload)
        .send()
        .await?;

    println!("Status code: {}", response.status());

    let json_response: Value = response.json().await?;
    println!("Response:");
    println!("{}", serde_json::to_string_pretty(&json_response)?);

    Ok(())