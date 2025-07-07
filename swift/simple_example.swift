// main.swift
import Foundation

func sendWebhookRequest() async throws {
    let webhookId = "YOUR_WEBHOOK_ID"
    let webhookCode = "YOUR_WEBHOOK_CODE"
    let url = URL(string: "https://psyll.com/env/webhook/\(webhookId)")!

    var request = URLRequest(url: url)
    request.httpMethod = "POST"
    request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")

    let payload = [
        "code": webhookCode,
        "action": "BUY",
        "quantity": "0.0034",
        "symbol": "BTCUSDT"
    ]
    let formData = payload.map { key, value in
        "\(key)=\(value.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? "")"
    }.joined(separator: "&")
    request.httpBody = formData.data(using: .utf8)

    let (data, response) = try await URLSession.shared.data(for: request)
    guard let httpResponse = response as? HTTPURLResponse else {
        throw URLError(.badServerResponse)
    }
    print("Status code: \(httpResponse.statusCode)")

    if let jsonString = String(data: data, encoding: .utf8) {
        print("Response:")
        print(jsonString)
    } else {
        throw URLError(.cannotDecodeRawData)
    }
}

Task {
    do {
        try await sendWebhookRequest()
    } catch {
        print("Error: \(error.localizedDescription)")
    }
}