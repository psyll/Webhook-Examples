import requests
import json

webhook_id = "YOUR_WEBHOOK_ID"
webhook_code = "YOUR_WEBHOOK_CODE"

url = "https://psyll.com/env/webhook/" + webhook_id

payload = {
    "code": webhook_code,
    "action": "BUY",
    "quantity": "0.0034",
    "symbol": "BTCUSDC"
}

response = requests.post(url, data=payload)

print("Status code:", response.status_code)

json_response = response.json()
print("Response:")
print(json.dumps(json_response, indent=4))
