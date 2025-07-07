const axios = require('axios');

const webhookId = "YOUR_WEBHOOK_ID";
const webhookCode = "YOUR_WEBHOOK_CODE";

const url = "https://psyll.com/env/webhook/" + webhookId;

const payload = {
    code: webhookCode,
    action: "BUY",
    quantity: "0.01",
    symbol: "BTCUSDT"
};

axios.post(url, payload)
    .then(response => {
        console.log("Response:");
        console.log(response.data);
    })
    .catch(error => {
        console.error("Error:", error);
    });