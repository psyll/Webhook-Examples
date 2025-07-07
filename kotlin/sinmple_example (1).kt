import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun main() {
    val webhookId = "YOUR_WEBHOOK_ID"
    val webhookCode = "YOUR_WEBHOOK_CODE"
    val url = "https://psyll.com/env/webhook/$webhookId"

    val client = OkHttpClient()
    val formBody = FormBody.Builder()
        .add("code", webhookCode)
        .add("action", "BUY")
        .add("quantity", "0.0034")
        .add("symbol", "BTCUSDT")
        .build()

    val request = Request.Builder()
        .url(url)
        .post(formBody)
        .build()

    try {
        client.newCall(request).execute().use { response ->
            println("Status code: ${response.code}")
            val responseBody = response.body?.string()
            println("Response:")
            println(responseBody)
        }
    } catch (e: IOException) {
        println("Error: ${e.message}")
    }
}