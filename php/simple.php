<?php

// Webhook identifier (replace with your actual webhook ID)
$webhook_id = "YOUR_WEBHOOK_ID";

// Webhook authentication code (replace with your actual webhook code)
$webhook_code = "YOUR_WEBHOOK_CODE";

// Construct the full webhook URL using the webhook ID
$url = "https://psyll.com/env/webhook/" . $webhook_id;

// Data to be sent in the POST request
$data = [
    "code" => $webhook_code,    // Authentication code
    "action" => "BUY",          // Trading action (e.g., BUY or SELL)
    "quantity" => "0.0034",     // Amount of cryptocurrency to trade
    "symbol" => "BTCUSDT"       // Trading pair symbol (Bitcoin to USDT)
];

// Initialize a cURL session with the webhook URL
$ch = curl_init($url);

// Return the response as a string instead of outputting it
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Set the POST fields using URL-encoded format
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($data));

// Execute the cURL request and store the response
$response = curl_exec($ch);

// Close the cURL session
curl_close($ch);

// Print the response from the server (could be JSON or plain text)
print_r($response);
