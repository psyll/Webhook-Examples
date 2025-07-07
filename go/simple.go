package main

import (
	"bytes"        // Provides functions for manipulating byte slices
	"encoding/json" // Provides JSON encoding and decoding
	"fmt"          // Provides formatting and printing functions
	"net/http"     // Provides HTTP client and server implementations
	"net/url"      // Provides URL parsing and query encoding
)

// main is the entry point of the application
func main() {
	// Define webhook configuration
	webhookID := "YOUR_WEBHOOK_ID"     // Unique identifier for the webhook
	webhookCode := "YOUR_WEBHOOK_CODE" // Authentication code for the webhook

	// Construct the webhook URL
	urlStr := "https://psyll.com/env/webhook/" + webhookID

	// Create form-encoded payload for the webhook request
	data := url.Values{}
	data.Set("code", webhookCode)     // Set webhook authentication code
	data.Set("action", "BUY")         // Specify the action to perform
	data.Set("quantity", "0.0034")    // Set quantity for the transaction
	data.Set("symbol", "BTCUSDT")     // Set trading pair symbol

	// Send POST request with form-encoded data to the webhook URL
	resp, err := http.PostForm(urlStr, data)
	if err != nil {
		// Handle and log errors from the HTTP request
		fmt.Printf("Error: %v\n", err)
		return
	}
	defer resp.Body.Close() // Ensure the response body is closed after use

	// Output the HTTP status code of the response
	fmt.Printf("Status code: %d\n", resp.StatusCode)

	// Decode the JSON response into a map
	var jsonResponse map[string]interface{}
	if err := json.NewDecoder(resp.Body).Decode(&jsonResponse); err != nil {
		// Handle and log errors during JSON decoding
		fmt.Printf("Error decoding JSON: %v\n", err)
		return
	}

	// Marshal the JSON response with indentation for readability
	jsonData, _ := json.MarshalIndent(jsonResponse, "", "    ")
	// Output the formatted JSON response
	fmt.Println("Response:")
	fmt.Println(string(jsonData))
}