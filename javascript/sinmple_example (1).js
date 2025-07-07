const webhook_id = "YOUR_WEBHOOK_ID";
const webhook_code = "YOUR_WEBHOOK_CODE";

const url = `https://psyll.com/env/webhook/${webhook_id}`;

const payload = {
    code: webhook_code,
    action: "BUY",
    quantity: "0.01",
    symbol: "BTCUSDT"
};

fetch(url, {
    method: "POST",
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"
    },
    body: new URLSearchParams(payload)
})
.then(response => response.json())
.then(data => {
    console.log("Response:");
    console.log(data);
})
.catch(error => {
    console.error("Error:", error);
});