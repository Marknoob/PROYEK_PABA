package petra.ac.id.proyekpaba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class SignUp : AppCompatActivity() {

    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val inputEmail = findViewById<EditText>(R.id.ed_email)
        val inputUsername = findViewById<EditText>(R.id.ed_username)
        val inputPassword = findViewById<EditText>(R.id.ed_password)

        val _btnsignUp = findViewById<Button>(R.id.btn_signup)
        _btnsignUp.setOnClickListener {

            if (inputUsername.text.isNotEmpty() && inputPassword.text.isNotEmpty() && inputEmail.text.isNotEmpty()) {

                val dataBaru = Account(
                    inputEmail.text.toString(),
                    inputUsername.text.toString(),
                    inputPassword.text.toString()
                )

                db.collection("tbUserAccount")
                    .document(inputEmail.text.toString())
                    .set(dataBaru).addOnSuccessListener {
                        inputUsername.setText("")
                        inputUsername.setText("")
                        inputPassword.setText("")

                        Toast.makeText(
                            this,
                            "Data berhasil ditambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener { e ->
                        Log.d("PROJ_PABA", "Error adding document", e)
                    }

                val intent= Intent(this@SignUp, Login::class.java)
                startActivity(intent)
            } else {

            }

        }

        val _txtSignUp = findViewById<TextView>(R.id.txt_logIn)
        _txtSignUp.setOnClickListener {
            val intent= Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }
    }
}