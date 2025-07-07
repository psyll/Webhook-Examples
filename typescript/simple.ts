// Webhook ID and authentication code (replace with your actual values)
const webhookId: string = "YOUR_WEBHOOK_ID";
const webhookCode: string = "YOUR_WEBHOOK_CODE";

// Construct the webhook URL using template literals
const url: string = `https://psyll.com/env/webhook/${webhookId}`;

// Prepare the payload to send as form data
const payload = {
    code: webhookCode,      // Authentication code
    action: "BUY",          // Action to perform (BUY or SELL)
    quantity: "0.0034",     // Amount to trade
    symbol: "BTCUSDT"       // Trading pair symbol
};

// Send the POST request using fetch API
fetch(url, {
    method: "POST",
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"  // Form data content type
    },
    body: new URLSearchParams(payload)  // Encode payload as URL-encoded string
})
.then(response => {
    console.log(`Status code: ${response.status}`);  // Log the HTTP status code
    return response.json();                          // Parse the response as JSON
})
.then(data => {
    console.log("Response:");
    console.log(JSON.stringify(data, null, 4));     // Pretty-print the JSON response
})
.catch(error => {
    console.error("Error:", error);                  // Handle any errors
});
