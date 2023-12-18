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
        val edt_weight = findViewById<EditText>(R.id.ed_weight)
        val edt_height = findViewById<EditText>(R.id.ed_height)

        val btnPlusAge = findViewById<Button>(R.id.btn_plusAge)
        val btnMinusAge = findViewById<Button>(R.id.btn_minusAge)
        val btnPlusWeight = findViewById<Button>(R.id.btn_plusWeight)
        val btnMinusWeight = findViewById<Button>(R.id.btn_minusWeight)
        val btnPlusHeight = findViewById<Button>(R.id.btn_plusHeight)
        val btnMinusHeight = findViewById<Button>(R.id.btn_minusHeight)

        // Age
        var ageValue: Int
        btnPlusAge.setOnClickListener {
            if(edt_age.text.isNotEmpty()) {
                ageValue = Integer.parseInt(edt_age.text.toString())
                edt_age.setText("${ageValue + 1}")
            } else {
                edt_age.setText("1")
            }
        }
        btnMinusAge.setOnClickListener {
            if(edt_age.text.isNotEmpty()) {
                ageValue = Integer.parseInt(edt_age.text.toString())
                if (ageValue > 0) {
                    edt_age.setText("${ageValue - 1}")
                }
            } else {
                edt_age.setText("0")
            }
        }

        // Weight
        var weightValue: Int
        btnPlusWeight.setOnClickListener {
            if(edt_weight.text.isNotEmpty()) {
                weightValue = Integer.parseInt(edt_weight.text.toString())
                edt_weight.setText("${weightValue + 1}")
            } else {
                edt_weight.setText("1")
            }
        }
        btnMinusWeight.setOnClickListener {
            if(edt_weight.text.isNotEmpty()) {
                weightValue = Integer.parseInt(edt_weight.text.toString())
                if (weightValue > 0) {
                    edt_weight.setText("${weightValue - 1}")
                }
            } else {
                edt_weight.setText("0")
            }
        }

        // Height
        var heightValue: Int
        btnPlusHeight.setOnClickListener {
            if(edt_height.text.isNotEmpty()) {
                heightValue = Integer.parseInt(edt_height.text.toString())
                edt_height.setText("${heightValue + 1}")
            } else {
                edt_weight.setText("1")
            }
        }
        btnMinusHeight.setOnClickListener {
            if(edt_height.text.isNotEmpty()) {
                heightValue = Integer.parseInt(edt_height.text.toString())
                if (heightValue > 0) {
                    edt_height.setText("${heightValue - 1}")
                }
            } else {
                edt_height.setText("0")
            }
        }



        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        btnCalculate.setOnClickListener {
            println("Tertekan")
        }
    }
}