package petra.ac.id.proyekpaba.calvin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import petra.ac.id.proyekpaba.R

class Edit_Bodyfat : AppCompatActivity() {

    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bodyfat)
    }
}