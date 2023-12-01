package petra.ac.id.proyekpaba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val inputUsername = findViewById<EditText>(R.id.ed_username)
        val inputPassword = findViewById<EditText>(R.id.ed_password)
        val inputemail = findViewById<EditText>(R.id.ed_email)

        val _btnsignUp = findViewById<Button>(R.id.btn_signup)
        _btnsignUp.setOnClickListener {
            val intent= Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }

        val _txtSignUp = findViewById<TextView>(R.id.txt_logIn)
        _txtSignUp.setOnClickListener {
            val intent= Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }
    }
}