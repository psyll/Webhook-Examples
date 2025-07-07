// Import the axios library for making HTTP requests
const axios = require('axios');

// Define webhook configuration variables
const webhookId = "YOUR_WEBHOOK_ID"; // Unique identifier for the webhook
const webhookCode = "YOUR_WEBHOOK_CODE"; // Authentication code for the webhook

// Construct the webhook URL using string concatenation
const url = "https://psyll.com/env/webhook/" + webhookId;

// Define the payload object for the webhook request
const payload = {
    code: webhookCode, // Webhook authentication code
    action: "BUY", // Specify the action to perform
    quantity: "0.01", // Quantity for the transaction
    symbol: "BTCUSDT" // Trading pair symbol
};

// Send asynchronous POST request to the webhook URL using axios
axios.post(url, payload)
    .then(response => {
        // Output the response data to the console
        console.log("Response:");
        console.log(response.data);
    })
    .catch(error => {
        // Handle and log any errors that occur during the request
        console.error("Error:", error);
    });