using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Collections.Specialized;

class Program
{
    static async Task Main(string[] args)
    {
        try
        {
            string webhookId = "YOUR_WEBHOOK_ID";
            string webhookCode = "YOUR_WEBHOOK_CODE";
            string url = $"https://psyll.com/env/webhook/{webhookId}";

            using HttpClient client = new HttpClient();
            var payload = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("code", webhookCode),
                new KeyValuePair<string, string>("action", "BUY"),
                new KeyValuePair<string, string>("quantity", "0.0034"),
                new KeyValuePair<string, string>("symbol", "BTCUSDT")
            });

            HttpResponseMessage response = await client.PostAsync(url, payload);
            Console.WriteLine($"Status code: {(int)response.StatusCode}");

            string jsonResponse = await response.Content.ReadAsStringAsync();
            Console.WriteLine("Response:");
            Console.WriteLine(jsonResponse);
        }
        catch (Exception ex)
        {
            Console.Error.WriteLine($"Error: {ex.Message}");
        }
    }
}