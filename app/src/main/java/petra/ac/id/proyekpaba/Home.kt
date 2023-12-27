package petra.ac.id.proyekpaba

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import petra.ac.id.proyekpaba.calvin.Body_Fat_Percentage
import petra.ac.id.proyekpaba.markus.BMI_Calculator
import java.text.DateFormat
import java.util.Calendar

class Home: AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

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

        // Untuk data card last test
        val tvAge = findViewById<TextView>(R.id.tv_Age)
        val tvWeight = findViewById<TextView>(R.id.tv_Weight)
        val tvHeight = findViewById<TextView>(R.id.tv_Height)
        val tvScore = findViewById<TextView>(R.id.tv_scoreBMI)
        val tvStatus = findViewById<TextView>(R.id.tv_statusBMI)

        // Ambil data terakhir dari tbBMI_Result berdasarkan nama
        val userResultRef = db.collection("tbBMI_Result")
            .whereEqualTo("namaAkun", userName)
            .orderBy("date", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(1)

        userResultRef.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result != null && !result.isEmpty) {
                        val lastResult = result.documents[0]
                        // Tampilkan data pada TextView
                        tvAge.text = "${lastResult.getString("age")}"
                        tvWeight.text = "${lastResult.getString("weight")}"
                        tvHeight.text = "${lastResult.getString("height")}"
                        tvScore.text = "${lastResult.getString("scoreBMI")}"
                        tvStatus.text = "${lastResult.getString("statusBMI")}"
                    } else {
                        tvAge.text = "Age: Data tidak ditemukan"
                        tvWeight.text = "Weight: Data tidak ditemukan"
                        tvHeight.text = "Height: Data tidak ditemukan"
                        tvScore.text = "BMI Score: Data tidak ditemukan"
                        tvStatus.text = "BMI Status: Data tidak ditemukan"
                    }
                }
            }

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

    }
}
