package com.example.login_sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    var sharedpref: SharedPreferences? = null
    var sharedprefEdit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedpref = getSharedPreferences("pertemuan9", Context.MODE_PRIVATE)
        sharedprefEdit = sharedpref?.edit()

        //Pengecekan sudah login atau belum
        if(sharedpref?.getBoolean("isLogin", false) == true) {
            setContentView(R.layout.activity_user)

            val btnLogout = findViewById<Button>(R.id.logout)
//            val welcomeuser = findViewById<TextView>(R.id.welcomeUser)

            //Button untuk Logout
            btnLogout.setOnClickListener {
                sharedprefEdit?.putBoolean("isLogin", false)
                sharedprefEdit?.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            //Untuk Pengaturan Bahasa
            val pilBahasa = findViewById<Spinner>(R.id.pilBahasa)
            val adapter = ArrayAdapter.createFromResource(this, R.array.language_option, R.layout.support_simple_spinner_dropdown_item)
            pilBahasa.adapter = adapter
            pilBahasa.setSelection(sharedpref!!.getInt("Pilihan_Bahasa", 0))
            pilBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    sharedprefEdit?.putInt("Pilihan_Bahasa", position)
                    sharedprefEdit?.apply()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            //Untuk Pengaturan Ukuran Font
            val edtUkuran = findViewById<EditText>(R.id.editUkuran)
            edtUkuran.setText(sharedpref!!.getString("Ukuran_Font", "12"))
            edtUkuran.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    sharedprefEdit?.putString("Ukuran_Font", s.toString())
                    sharedprefEdit?.apply()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            })
        }
        else {
            setContentView(R.layout.activity_main)
            val username = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            val btnLogin = findViewById<Button>(R.id.login)

            btnLogin.setOnClickListener {
                if(username.text.toString() == "Timmie" && password.text.toString() == "andro123") {
                    sharedprefEdit?.putBoolean("isLogin", true)
                    sharedprefEdit?.apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }

    }
}