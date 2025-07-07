require 'httparty'  # Library for making HTTP requests
require 'json'      # Library for working with JSON data

# Webhook identifier (replace with your actual ID)
webhook_id = "YOUR_WEBHOOK_ID"

# Webhook authentication code (replace with your actual code)
webhook_code = "YOUR_WEBHOOK_CODE"

# Construct the webhook URL using string interpolation
url = "https://psyll.com/env/webhook/#{webhook_id}"

# Data to be sent in the POST request
payload = {
  code: webhook_code,       # Authentication code
  action: "BUY",            # Action to perform (BUY or SELL)
  quantity: "0.0034",       # Amount of cryptocurrency to trade
  symbol: "BTCUSDT"         # Trading pair symbol
}

# Send a POST request with the payload
response = HTTParty.post(url, body: payload)

# Print the HTTP status code of the response
puts "Status code: #{response.code}"

# Pretty-print the JSON response from the server
puts "Response:"
puts JSON.pretty_generate(response.parsed_response)
