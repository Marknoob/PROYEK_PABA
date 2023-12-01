package petra.ac.id.proyekpaba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputUsername = findViewById<EditText>(R.id.ed_username)
        val inputPassword = findViewById<EditText>(R.id.ed_password)

        val _btnLogin = findViewById<Button>(R.id.btn_login)
        _btnLogin.setOnClickListener {
            val intent= Intent(this@Login, Home::class.java)
            startActivity(intent)
        }

        val _txtForgotPass = findViewById<TextView>(R.id.txt_forgotPassword)
        _txtForgotPass.setOnClickListener {
            // Toast "E-mail has sent for reset password"
        }

        val _txtSignUp = findViewById<TextView>(R.id.txt_signUp)
        _txtSignUp.setOnClickListener {
            val intent= Intent(this@Login, SignUp::class.java)
            startActivity(intent)
        }
    }


}