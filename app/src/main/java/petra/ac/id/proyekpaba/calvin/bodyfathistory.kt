package petra.ac.id.proyekpaba.calvin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import petra.ac.id.proyekpaba.Home
import petra.ac.id.proyekpaba.R

class bodyfathistory : AppCompatActivity() {

    val db = Firebase.firestore

    var data_bodyfathistory = ArrayList<databodyfat>()
    private lateinit var _rvbodyfathistory: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bodyfathistory)

        _rvbodyfathistory = findViewById(R.id.rv_bodyfathistory)

        tampilkanData()

        val _btnBacktoHome = findViewById<Button>(R.id.btn_BackToHome)
        _btnBacktoHome.setOnClickListener {
            val intent = Intent(this@bodyfathistory, Home::class.java)
            startActivity(intent)
        }
    }

    fun tampilkanData() {
        db.collection("tbbodyfat")
            .get()
            .addOnSuccessListener {
                    result ->
                data_bodyfathistory.clear()
                for (document in result) {
                    val hasil = databodyfat(
                        document.data["age"].toString(),
                        document.data["gender"].toString(),
                        document.data["weight"].toString(),
                        document.data["height"].toString(),
                        document.data["neck"].toString(),
                        document.data["waist"].toString(),
                        document.data["hip"].toString(),
                        document.data["navyMethod"].toString(),
                        document.data["category"].toString(),
                        document.data["fatMass"].toString(),
                        document.data["leanBodyMass"].toString(),
                        document.data["bmiMethod"].toString()
                    )
                    data_bodyfathistory.add(hasil)
                }

                if (data_bodyfathistory.isNotEmpty()) {

                    val adapterP = adapterBodyFat(data_bodyfathistory)
                    _rvbodyfathistory.adapter = adapterP
                    _rvbodyfathistory.layoutManager = LinearLayoutManager(this)

                    adapterP.setOnItemClickCallback(object :
                        adapterBodyFat.OnItemClickCallback {
                        override fun onItemClicked(data: databodyfat) {
                            val intent = Intent(this@bodyfathistory, Body_Fat_Percentage::class.java)
                            intent.putExtra("kirimDatabodyfat", data)
                            /*intent.putExtra("age", data.age)
                            intent.putExtra("gender", data.gender)
                            intent.putExtra("weight", data.weight)
                            intent.putExtra("height", data.height)
                            intent.putExtra("neck", data.neck)
                            intent.putExtra("waist", data.waist)
                            intent.putExtra("hip", data.hip)
                            intent.putExtra("navyMethod", data.navyMethod)
                            intent.putExtra("category", data.category)
                            intent.putExtra("fatMass", data.fatMass)
                            intent.putExtra("leanBodyMass", data.leanBodyMass)
                            intent.putExtra("bmiMethod", data.bmiMethod)*/
                            startActivity(intent)
                        }
                    })
                } else {
                    Toast.makeText(
                        this,
                        "Data Tidak Tersedia",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}

