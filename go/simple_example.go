package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"net/url"
)

func main() {
	webhookID := "YOUR_WEBHOOK_ID"
	webhookCode := "YOUR_WEBHOOK_CODE"

	urlStr := "https://psyll.com/env/webhook/" + webhookID

	data := url.Values{}
	data.Set("code", webhookCode)
	data.Set("action", "BUY")
	data.Set("quantity", "0.0034")
	data.Set("symbol", "BTCUSDT")

	resp, err := http.PostForm(urlStr, data)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return
	}
	defer resp.Body.Close()

	fmt.Printf("Status code: %d\n", resp.StatusCode)

	var jsonResponse map[string]interface{}
	if err := json.NewDecoder(resp.Body).Decode(&jsonResponse); err != nil {
		fmt.Printf("Error decoding JSON: %v\n", err)
		return
	}

	jsonData, _ := json.MarshalIndent(jsonResponse, "", "    ")
	fmt.Println("Response:")
	fmt.Println(string(jsonData))
}