package petra.ac.id.proyekpaba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputUsernameOrEmail = findViewById<EditText>(R.id.ed_usernameoremail)
        val inputPassword = findViewById<EditText>(R.id.ed_password)

        val _btnLogin = findViewById<Button>(R.id.btn_login)
        _btnLogin.setOnClickListener {

            val usernameOrEmail = inputUsernameOrEmail.text.toString()
            val password = inputPassword.text.toString()

            if (usernameOrEmail.isNotEmpty() && password.isNotEmpty()) {

                var isEmailExist: Boolean
                var isUsernameExist: Boolean

                // Check apakah email sudah pernah dibuat
                db.collection("tbUserAccount")
                    .whereEqualTo("emailAkun", usernameOrEmail)
                    .get()
                    .addOnSuccessListener { result ->
                        isEmailExist = !result.isEmpty

                        // Check apakah username sudah pernah dibuat
                        db.collection("tbUserAccount")
                            .whereEqualTo("namaAkun", usernameOrEmail)
                            .get()
                            .addOnSuccessListener { resultUsername ->
                                isUsernameExist = !resultUsername.isEmpty

                                if (!isEmailExist && !isUsernameExist) {
                                    Toast.makeText(
                                        this,
                                        "Akun tidak ditemukan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if(isEmailExist) {
                                    // Check Password
                                    val userDoc = result.documents[0]
                                    val storedPassword = userDoc.getString("passwordAkun")

                                    if (storedPassword == password) {
                                        val intent = Intent(this@Login, Home::class.java)
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Password salah",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                } else if(isUsernameExist) {
                                    // Check Password
                                    val userDoc = resultUsername.documents[0]
                                    val storedPassword = userDoc.getString("passwordAkun")

                                    if (storedPassword == password) {
                                        val intent = Intent(this@Login, Home::class.java)
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Password salah",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                    }
            } else {
                Toast.makeText(
                    this,
                    "Data tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val _txtForgotPass = findViewById<TextView>(R.id.txt_forgotPassword)
        _txtForgotPass.setOnClickListener {
            // Add code for password reset functionality
            // Toast.makeText(this, "E-mail has sent for reset password", Toast.LENGTH_SHORT).show()
        }

        val _txtSignUp = findViewById<TextView>(R.id.txt_signUp)
        _txtSignUp.setOnClickListener {
            val intent = Intent(this@Login, SignUp::class.java)
            startActivity(intent)
        }
    }
}
