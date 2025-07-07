// main.swift
import Foundation

// Async function to send the webhook POST request
func sendWebhookRequest() async throws {
    // Webhook ID and authentication code (replace with real values)
    let webhookId = "YOUR_WEBHOOK_ID"
    let webhookCode = "YOUR_WEBHOOK_CODE"

    // Construct the webhook URL using string interpolation
    let url = URL(string: "https://psyll.com/env/webhook/\(webhookId)")!

    // Create a URLRequest and configure it for a POST request
    var request = URLRequest(url: url)
    request.httpMethod = "POST"
    // Set the content type to URL-encoded form data
    request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")

    // Prepare the payload as key-value pairs
    let payload = [
        "code": webhookCode,    // Authentication code
        "action": "BUY",        // Action to perform
        "quantity": "0.0034",   // Amount to trade
        "symbol": "BTCUSDT"     // Trading pair symbol
    ]

    // Encode the payload as URL-encoded form data string
    let formData = payload.map { key, value in
        "\(key)=\(value.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? "")"
    }.joined(separator: "&")

    // Convert the form data string to Data and assign it as the request body
    request.httpBody = formData.data(using: .utf8)

    // Perform the asynchronous network request
    let (data, response) = try await URLSession.shared.data(for: request)

    // Verify that the response is an HTTP response and get the status code
    guard let httpResponse = response as? HTTPURLResponse else {
        throw URLError(.badServerResponse)
    }
    print("Status code: \(httpResponse.statusCode)")

    // Try to convert the response data to a string and print it
    if let jsonString = String(data: data, encoding: .utf8) {
        print("Response:")
        print(jsonString)
    } else {
        throw URLError(.cannotDecodeRawData)
    }
}

// Run the async function inside a Task and handle errors
Task {
    do {
        try await sendWebhookRequest()
    } catch {
        print("Error: \(error.localizedDescription)")
    }
}
