package petra.ac.id.proyekpaba

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class adapter_BMI_Calculator_Page: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_calculator_page)

        val edt_age = findViewById<EditText>(R.id.ed_age)
//        val ageValue = Integer.parseInt(edt_age.text.toString())
        val edt_weight = findViewById<EditText>(R.id.ed_weight)
        val edt_height = findViewById<EditText>(R.id.ed_height)

        val btnPlusAge = findViewById<Button>(R.id.btn_plusAge)
        val btnMinusAge = findViewById<Button>(R.id.btn_minusAge)

        var ageValue = 0
        btnPlusAge.setOnClickListener {
            if(edt_age != null) {
                ageValue = Integer.parseInt(edt_age.text.toString())
                edt_age.setText("${ageValue + 1}")
            }
        }

        btnMinusAge.setOnClickListener {
            ageValue = Integer.parseInt(edt_age.text.toString())
            if (ageValue > 0) {
                edt_age.setText("${ageValue - 1}")
            }
        }

        val btnPlusWeight = findViewById<Button>(R.id.btn_plusWeight)
        val btnMinusWeight = findViewById<Button>(R.id.btn_minusWeight)

        val btnPlusHeight = findViewById<Button>(R.id.btn_plusHeight)
        val btnMinusHeight = findViewById<Button>(R.id.btn_minusHeight)

        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        btnCalculate.setOnClickListener {
            println("Tertekan")
        }
    }
}