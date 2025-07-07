# Webhook Examples

This repository contains a collection of code examples demonstrating how to interact with a [Psyll webhook](https://psyll.com/en/webhok) endpoint  using various programming languages. Each example performs a POST request to the webhook with a payload to execute a trading action (e.g., "BUY" order for BTCUSDT). The examples are designed to be simple, reusable, and easily adaptable for different use cases.

## Whats is webhook?

Webhook enable executing trades across exchanges through simple HTTP POST requests. They are ideal for automating complex trading strategies, allowing bot developers to seamlessly integrate trading logic, respond instantly to alerts, and embed trading functionality into any backend system for complete automation.

## Create a webhook on [Psyll.com](https://psyll.com/en/webhok)

1) Navigate to [Psyll.com](https://psyll.com/en/webhok) website
2) Login to your user account
3) Navigate to **"My Webhooks"** under the user menu (top-right corner)

![alt text](_images/1.png "Title")


4) Click **"Create new webook"**

![alt text](_images/2.png "Title")

1) Fill in the webhook name, select the exchange, and provide your API keys

![alt text](_images/3.png "Title")


# Webhook request

Request URL:

```
https://psyll.com/env/webhook/{WEBHOOK-ID}
```

Request data

```json
{
  "code": "{WEBHOOK-CODE}",
  "action": "{ACTION}",
  "quantity": "{QUANTITY}",
  "symbol": "{SYMBOL}"
}
```


### Data descriptions

| Name | Type | Description |
| -------- | ------- | ------- |
| `code`| *string* |  unique authentication code required to validate the webhook request, ensuring secure access to the API. |
| `action` | *string* | Specifies the trading action to be performed.Allowed Values: "BUY", "SELL", "SET"
| `quantity` | *float* | Defines the amount of the asset to trade, represented as a string to maintain decimal precision.
| `symbol` | *string* | Identifies the trading pair or asset for the order. The symbol must match a valid trading pair supported by the exchange



# Webhook response

A successful webhook request returns the following JSON response:

```json
{
    "created": "2025-07-07 18:25:20",
    "limit": {
        "account": "PRO",
        "max_per_day": 200,
        "usage": 3
    },
    "message": "Order placed",
    "request": {
        "action": "SET",
        "code": "{WEBHOOK-CODE}",
        "quantity": "1",
        "symbol": "BTCUSDC"
    },
    "status": "success"
}

```

### Response Fields


| Field | Description |
|---|---|
created | Timestamp indicating when the request was processed.
limit | Object containing account tier, daily request limit, and current usage.
message | A brief description of the request outcome.
request | Echoes the submitted request data for verification.
status | Indicates the outcome of the request, e.g., "success" or "error".


# Programming languages examples

The repository includes example implementations in the following programming languages:

|     | Name |
| -------- | ------- |
| ![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)  | Python    |
| ![C#](https://img.shields.io/badge/c%23-%23239120.svg?style=for-the-badge&logo=csharp&logoColor=white) | C#     |
| ![PHP](https://img.shields.io/badge/php-%23777BB4.svg?style=for-the-badge&logo=php&logoColor=white)    | PHP    |
| ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)    | JavaScript    |
| ![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white)    | NodeJS    |
| ![Go](https://img.shields.io/badge/go-%2300ADD8.svg?style=for-the-badge&logo=go&logoColor=white)    | Go    |
| ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)    | Java    |
| ![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)    | Kotlin    |
| ![Ruby](https://img.shields.io/badge/ruby-%23CC342D.svg?style=for-the-badge&logo=ruby&logoColor=white)    | Ruby    |
| ![Rust](https://img.shields.io/badge/rust-%23000000.svg?style=for-the-badge&logo=rust&logoColor=white)    | Rust    |
| ![Swift](https://img.shields.io/badge/swift-F54A2A?style=for-the-badge&logo=swift&logoColor=white)    | Swift    |
| ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)    | TypeScript    |

















