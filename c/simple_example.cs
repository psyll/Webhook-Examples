using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Collections.Specialized;

class Program
{
    // Entry point of the application
    static async Task Main(string[] args)
    {
        try
        {
            // Define webhook configuration
            string webhookId = "YOUR_WEBHOOK_ID"; // Unique identifier for the webhook
            string webhookCode = "YOUR_WEBHOOK_CODE"; // Authentication code for the webhook
            string url = $"https://psyll.com/env/webhook/{webhookId}"; // Construct the webhook URL

            // Initialize HttpClient for making HTTP requests
            using HttpClient client = new HttpClient();

            // Create form-encoded payload for the webhook request
            var payload = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("code", webhookCode), // Webhook authentication code
                new KeyValuePair<string, string>("action", "BUY"), // Specify the action to perform
                new KeyValuePair<string, string>("quantity", "0.0034"), // Quantity for the transaction
                new KeyValuePair<string, string>("symbol", "BTCUSDT") // Trading pair symbol
            });

            // Send asynchronous POST request to the webhook URL
            HttpResponseMessage response = await client.PostAsync(url, payload);

            // Output the HTTP status code of the response
            Console.WriteLine($"Status code: {(int)response.StatusCode}");

            // Read and output the response content
            string jsonResponse = await response.Content.ReadAsStringAsync();
            Console.WriteLine("Response:");
            Console.WriteLine(jsonResponse);
        }
        catch (Exception ex)
        {
            // Handle and log any exceptions that occur during execution
            Console.Error.WriteLine($"Error: {ex.Message}");
        }
    }
}