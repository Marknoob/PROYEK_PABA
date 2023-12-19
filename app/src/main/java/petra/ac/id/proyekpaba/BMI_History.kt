package petra.ac.id.proyekpaba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BMI_History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_history)

        val _btnBacktoHome = findViewById<Button>(R.id.btn_BackToHome)
        val _rvBMI_History = findViewById<TextView>(R.id.rv_BMI_History)



        _btnBacktoHome.setOnClickListener {
            val intent = Intent(this@BMI_History, Home::class.java)
            startActivity(intent)
        }
    }
}