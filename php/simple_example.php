<?php

$webhook_id = "YOUR_WEBHOOK_ID";
$webhook_code = "YOUR_WEBHOOK_CODE";

$url = "https://psyll.com/env/webhook/" . $webhook_id;

$data = [
    "code" => $webhook_code,
    "action" => "BUY",
    "quantity" => "0.0034",
    "symbol" => "BTCUSDT"
];

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($data));

$response = curl_exec($ch);
curl_close($ch);
//echo '<pre>'; Format response
print_r($response);
//echo '</pre>';