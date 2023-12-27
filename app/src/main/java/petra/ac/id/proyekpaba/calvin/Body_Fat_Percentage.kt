package petra.ac.id.proyekpaba.calvin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import petra.ac.id.proyekpaba.R

class Body_Fat_Percentage : AppCompatActivity() {

    // Untuk menampung hasil response API
    data class BodyFatInfo(
        val navyMethod: Double,
        val category: String,
        val fatMass: Double,
        val leanBodyMass: Double,
        val bmiMethod: Double
    )

    // Untuk request API
    private suspend fun makeApiRequest(
        age: Int,
        gender: String,
        weight: Int,
        height: Int,
        neck: Int,
        waist: Int,
        hip: Int
    ): BodyFatInfo = withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val url =
            "https://fitness-calculator.p.rapidapi.com/bodyfat?age=$age&gender=$gender&weight=$weight&height=$height&neck=$neck&waist=$waist&hip=$hip"

        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", "ebf6499ff9msh042ad040d39fd71p1a58cajsn4ca87c1eff75")
            .addHeader("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        val responseBody = response.body()?.string() ?: "No response"

        // Untuk mengambil hasil response API
        // Parse the JSON response
        val jsonObject = JSONObject(responseBody)
        val dataObject = jsonObject.getJSONObject("data")

        // Extract relevant information
        val navyMethod = dataObject.getDouble("Body Fat (U.S. Navy Method)")
        val category = dataObject.getString("Body Fat Category")
        val fatMass = dataObject.getDouble("Body Fat Mass")
        val leanBodyMass = dataObject.getDouble("Lean Body Mass")
        val bmiMethod = dataObject.getDouble("Body Fat (BMI method)")

        return@withContext BodyFatInfo(navyMethod, category, fatMass, leanBodyMass, bmiMethod)
    }

    private val db = FirebaseFirestore.getInstance()


    /*
    private suspend fun makeApiRequest(): String = withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://fitness-calculator.p.rapidapi.com/bodyfat?age=25&gender=male&weight=70&height=178&neck=50&waist=96&hip=92")
            .get()
            .addHeader("X-RapidAPI-Key", "d7d9e0b0d0msh4ca54b57a1aaaadp117c7cjsnc24d5d92480b")
            .addHeader("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        return@withContext response.body()?.string() ?: "No response"
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_fat_percentage)

        /*// Launch a coroutine to perform the API request in the background
        GlobalScope.launch(Dispatchers.Main) {
            val apiResponse = makeApiRequest()
            println(apiResponse)
        }*/

        val edt_age = findViewById<EditText>(R.id.ed_age)
        val edt_weight = findViewById<EditText>(R.id.ed_weight)
        val edt_height = findViewById<EditText>(R.id.ed_height)
        val edt_neck = findViewById<EditText>(R.id.ed_neck)
        val edt_waist = findViewById<EditText>(R.id.ed_waist)
        val edt_hip = findViewById<EditText>(R.id.ed_hip)

        val rdb_male = findViewById<RadioButton>(R.id.rb_male)
        val rdb_female = findViewById<RadioButton>(R.id.rb_female)

        val btn_plusAge = findViewById<Button>(R.id.btn_plusAge)
        val btn_minusAge = findViewById<Button>(R.id.btn_minusAge)
        val btn_plusWeight = findViewById<Button>(R.id.btn_plusWeight)
        val btn_minusWeight = findViewById<Button>(R.id.btn_minusWeight)
        val btn_plusHeight = findViewById<Button>(R.id.btn_plusHeight)
        val btn_minusHeight = findViewById<Button>(R.id.btn_minusHeight)
        val btn_plusNeck = findViewById<Button>(R.id.btn_plusNeck)
        val btn_minusNeck = findViewById<Button>(R.id.btn_minusNeck)
        val btn_plusWaist = findViewById<Button>(R.id.btn_plusWaist)
        val btn_minusWaist = findViewById<Button>(R.id.btn_minusWaist)
        val btn_plusHip = findViewById<Button>(R.id.btn_plusHip)
        val btn_minusHip = findViewById<Button>(R.id.btn_minusHip)

        val btn_calculate = findViewById<Button>(R.id.btn_calculate)



        // Contoh penggunaan
        GlobalScope.launch(Dispatchers.Main) {
            val bodyFatInfo = makeApiRequest(25, "male", 70, 178, 50, 96, 92)
            println("Navy Method Body Fat: ${bodyFatInfo.navyMethod}")
            println("Body Fat Category: ${bodyFatInfo.category}")
            println("Body Fat Mass: ${bodyFatInfo.fatMass}")
            println("Lean Body Mass: ${bodyFatInfo.leanBodyMass}")
            println("BMI Method Body Fat: ${bodyFatInfo.bmiMethod}")
        }

    }
}