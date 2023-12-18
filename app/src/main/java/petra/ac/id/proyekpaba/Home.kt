package petra.ac.id.proyekpaba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.DateFormat
import java.util.Calendar

class Home: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime())

        val date = findViewById<TextView>(R.id.tanggal)
        date.setText(currentDate)

        // Launch a coroutine to perform the API request in the background
        GlobalScope.launch(Dispatchers.Main) {
            val apiResponse = makeApiRequest()
            println(apiResponse)
        }
    }

    private suspend fun makeApiRequest(): String = withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://fitness-calculator.p.rapidapi.com/bmi?age=20&weight=94&height=175")
            .get()
            .addHeader("X-RapidAPI-Key", "d7d9e0b0d0msh4ca54b57a1aaaadp117c7cjsnc24d5d92480b")
            .addHeader("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        return@withContext response.body()?.string() ?: "No response"
    }
}