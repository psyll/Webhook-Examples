import requests  # Library for making HTTP requests
import json      # Library for working with JSON data

# Webhook identifier (replace with your actual ID)
webhook_id = "YOUR_WEBHOOK_ID"

# Webhook authentication code (replace with your actual code)
webhook_code = "YOUR_WEBHOOK_CODE"

# Construct the webhook URL using the webhook ID
url = "https://psyll.com/env/webhook/" + webhook_id

# Data to be sent in the POST request
payload = {
    "code": webhook_code,       # Authentication code
    "action": "BUY",            # Action to perform (e.g., BUY or SELL)
    "quantity": "0.0034",       # Amount of cryptocurrency to trade
    "symbol": "BTCUSDC"         # Trading pair symbol (Bitcoin to USDC)
}

# Send the POST request with the payload
response = requests.post(url, data=payload)

# Print the HTTP status code of the response
print("Status code:", response.status_code)

# Parse and print the JSON response from the server, if available
json_response = response.json()
print("Response:")
print(json.dumps(json_response, indent=4))
