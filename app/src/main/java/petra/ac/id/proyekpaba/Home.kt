package petra.ac.id.proyekpaba

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.DateFormat
import java.util.Calendar

class Home: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Untuk tanggal
        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime())

        val date = findViewById<TextView>(R.id.tanggal)
        date.setText(currentDate)

        val name = findViewById<TextView>(R.id.name)
        // Menggunakan shared preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "")
        name.text = "Hi, $userName"


        val _btnTestBMI = findViewById<Button>(R.id.btn_TestBMI)
        _btnTestBMI.setOnClickListener {
            val intent = Intent(this@Home, BMI_Calculator::class.java)
            startActivity(intent)
        }
    }
}