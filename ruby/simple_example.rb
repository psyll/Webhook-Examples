require 'httparty'
require 'json'

webhook_id = "YOUR_WEBHOOK_ID"
webhook_code = "YOUR_WEBHOOK_CODE"

url = "https://psyll.com/env/webhook/#{webhook_id}"

payload = {
  code: webhook_code,
  action: "BUY",
  quantity: "0.0034",
  symbol: "BTCUSDT"
}

response = HTTParty.post(url, body: payload)

puts "Status code: #{response.code}"
puts "Response:"
puts JSON.pretty_generate(response.parsed_response)