package petra.ac.id.proyekpaba.calvin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import petra.ac.id.proyekpaba.R

class Daily_Calorie_Requirements : AppCompatActivity() {

    // Untuk menampung hasil response API
//    data class DCinfo(val score: Double)

    // Untuk request API
    /*private suspend fun makeApiRequest(age: Int, gender: String, height: Int, weight: Int, activitylevel: String): DCinfo =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()

            val url =
                "https://fitness-calculator.p.rapidapi.com/dailycalorie?age=$age&gender=$gender&height=$height&weight=$weight&activitylevel=$activitylevel"

            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "ebf6499ff9msh042ad040d39fd71p1a58cajsn4ca87c1eff75")
                .addHeader("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body()?.string() ?: "No response"
            println(responseBody)
            // Untuk mengambil hasil response API
            // Parse the JSON response
            val jsonObject = JSONObject(responseBody)
            val dataObject = jsonObject.getJSONObject("data")

            // Extract relevant information
            val score = dataObject.getDouble("ideal_weight")

            return@withContext DCinfo(score)
        }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_calorie_requirements)
    }
}