// Define webhook configuration variables
const webhook_id = "YOUR_WEBHOOK_ID"; // Unique identifier for the webhook
const webhook_code = "YOUR_WEBHOOK_CODE"; // Authentication code for the webhook

// Construct the webhook URL using template literals
const url = `https://psyll.com/env/webhook/${webhook_id}`;

// Define the payload object for the webhook request
const payload = {
    code: webhook_code, // Webhook authentication code
    action: "BUY", // Specify the action to perform
    quantity: "0.01", // Quantity for the transaction
    symbol: "BTCUSDT" // Trading pair symbol
};

// Send asynchronous POST request to the webhook URL
fetch(url, {
    method: "POST", // Set HTTP request method to POST
    headers: {
        "Content-Type": "application/x-www-form-urlencoded" // Set content type for form-encoded data
    },
    body: new URLSearchParams(payload) // Convert payload object to URL-encoded form data
})
.then(response => response.json()) // Parse the response as JSON
.then(data => {
    // Output the response data to the console
    console.log("Response:");
    console.log(data);
})
.catch(error => {
    // Handle and log any errors that occur during the request
    console.error("Error:", error);
});