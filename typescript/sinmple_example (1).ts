const webhookId: string = "YOUR_WEBHOOK_ID";
const webhookCode: string = "YOUR_WEBHOOK_CODE";
const url: string = `https://psyll.com/env/webhook/${webhookId}`;

const payload = {
    code: webhookCode,
    action: "BUY",
    quantity: "0.0034",
    symbol: "BTCUSDT"
};

fetch(url, {
    method: "POST",
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"
    },
    body: new URLSearchParams(payload)
})
.then(response => {
    console.log(`Status code: ${response.status}`);
    return response.json();
})
.then(data => {
    console.log("Response:");
    console.log(JSON.stringify(data, null, 4));
})
.catch(error => {
    console.error("Error:", error);
});