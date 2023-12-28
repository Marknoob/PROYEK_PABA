package petra.ac.id.proyekpaba

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import petra.ac.id.proyekpaba.Billy.Daily_Calorie_Requirements
import petra.ac.id.proyekpaba.calvin.Body_Fat_Percentage
import petra.ac.id.proyekpaba.calvin.databodyfat
import petra.ac.id.proyekpaba.markus.BMI_Calculator
import java.text.DateFormat
import java.util.Calendar

class Home : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    lateinit var dataUser: DataDiri

    // API Request For BodyFat
    private suspend fun makeApiRequest(
        age: Int,
        gender: String,
        weight: Int,
        height: Int,
        neck: Int,
        waist: Int,
        hip: Int
    ): Body_Fat_Percentage.BodyFatInfo = withContext(Dispatchers.IO) {
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

        return@withContext Body_Fat_Percentage.BodyFatInfo(
            navyMethod,
            category,
            fatMass,
            leanBodyMass,
            bmiMethod
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Untuk tanggal
        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)

        val date = findViewById<TextView>(R.id.tanggal)
        date.text = currentDate

        val name = findViewById<TextView>(R.id.name)
        // Menggunakan shared preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "")
        name.text = "Hi, $userName"



        // Code For BodyFat
        // Mengambil data user dari database
        if (userName != null) {
            db.collection("tbUserData")
                .get()
                .addOnSuccessListener { result ->

                    for (document in result) {
                        val hasil = DataDiri(
                            document.data["age"].toString(),
                            document.data["gender"].toString(),
                            document.data["weight"].toString(),
                            document.data["height"].toString(),
                            document.data["neck"].toString(),
                            document.data["waist"].toString(),
                            document.data["hip"].toString()
                        )
                        dataUser = hasil
                    }
                }
        }
        print("HASIL DATA: $dataUser")

        /*
                val score_navymethod = findViewById<TextView>(R.id.score_navyMethod)
                val score_category = findViewById<TextView>(R.id.score_Category)
                val score_fatmass = findViewById<TextView>(R.id.score_fatMass)
                val score_leanbodymass = findViewById<TextView>(R.id.score_leanBodyMass)
                val score_bmimethod = findViewById<TextView>(R.id.score_bmiMethod)

                if (dataUser != null) {
                    val age = dataUser.age.toInt()
                    val gender = dataUser.gender
                    val weight = dataUser.weight.toInt()
                    val height = dataUser.height.toInt()
                    val neck = dataUser.neck.toInt()
                    val waist = dataUser.waist.toInt()
                    val hip = dataUser.hip.toInt()

                    //memanggil API
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            val bodyFatInfo = makeApiRequest(age, gender, weight, height, neck, waist, hip)

                            score_navymethod.text = "Body Fat (U.S. Navy Method): ${bodyFatInfo.navyMethod}"
                            score_category.text = "Body Fat Category: ${bodyFatInfo.category}"
                            score_fatmass.text = "Body Fat Mass: ${bodyFatInfo.fatMass}"
                            score_leanbodymass.text = "Lean Body Mass: ${bodyFatInfo.leanBodyMass}"
                            score_bmimethod.text = "Body Fat (BMI method): ${bodyFatInfo.bmiMethod}"
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@Home,
                                "Terjadi kesalahan saat mengambil informasi body fat",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Data Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        */

        // Untuk data card last test
        val tvAge = findViewById<TextView>(R.id.tv_Age)
        val tvWeight = findViewById<TextView>(R.id.tv_Weight)
        val tvHeight = findViewById<TextView>(R.id.tv_Height)
        val tvScore = findViewById<TextView>(R.id.tv_scoreBMI)
        val tvStatus = findViewById<TextView>(R.id.tv_statusBMI)

        // Ambil data terakhir dari tbBMI_Result berdasarkan nama
//        val userResultRef = db.collection("tbBMI_Result")
//            .whereEqualTo("namaAkun", userName)
//            .orderBy("date", com.google.firebase.firestore.Query.Direction.DESCENDING)
//            .limit(1)
//
//        userResultRef.get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val result = task.result
//                    if (result != null && !result.isEmpty) {
//                        val lastResult = result.documents[0]
//                        // Tampilkan data pada TextView
//                        tvAge.text = "${lastResult.getString("age")}"
//                        tvWeight.text = "${lastResult.getString("weight")}"
//                        tvHeight.text = "${lastResult.getString("height")}"
//                        tvScore.text = "${lastResult.getString("scoreBMI")}"
//                        tvStatus.text = "${lastResult.getString("statusBMI")}"
//                    } else {
//                        tvAge.text = "Nan"
//                        tvWeight.text = "Nan"
//                        tvHeight.text = "Nan"
//                        tvScore.text = "Nan"
//                        tvStatus.text = "Nan"
//                    }
//                }
//            }


        val btnTestBMI = findViewById<Button>(R.id.btn_TestBMI)
        btnTestBMI.setOnClickListener {
            val intent = Intent(this@Home, BMI_Calculator::class.java)
            startActivity(intent)
        }

        val btnSetting = findViewById<ImageView>(R.id.btn_setting)
        btnSetting.setOnClickListener {
            val intent = Intent(this@Home, Setting::class.java)
            startActivity(intent)
        }

        val btn_bodyfat = findViewById<Button>(R.id.btnbodyfat)
        btn_bodyfat.setOnClickListener {
            val intent = Intent(this@Home, Body_Fat_Percentage::class.java)
            startActivity(intent)
        }

        val _btnDailyCalorie = findViewById<Button>(R.id.btn_daily_calorie)
        _btnDailyCalorie.setOnClickListener {
            val intent = Intent(this@Home, Daily_Calorie_Requirements::class.java)
            startActivity(intent)
        }

        val _btnDataDiri = findViewById<Button>(R.id.btn_dataDiri)
        _btnDataDiri.setOnClickListener {
            val intent = Intent(this@Home, FormDataDiri::class.java)
            startActivity(intent)
        }
    }
}
