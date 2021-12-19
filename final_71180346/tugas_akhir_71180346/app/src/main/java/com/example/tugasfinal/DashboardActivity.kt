package com.example.tugasfinal

import android.app.AlertDialog
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tugasfinal.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var binding:ActivityDashboardBinding

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_dashboard)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // menghilangkan actionbar
        getSupportActionBar()?.hide()

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val name_txt = findViewById<TextView>(R.id.name_txt)

        name_txt.text = currentUser?.displayName

        Glide.with(this).load(currentUser?.photoUrl).into(profile_image)

        sign_out_btn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }


        //Fungsi Button Add Data
        binding.btnCreate.setOnClickListener(View.OnClickListener {
            if(binding.nomorIdentitas.text.isNotEmpty() && binding.namaPenduduk.text.isNotEmpty()
                && binding.usiaPenduduk.text.isNotEmpty() && binding.alamatPenduduk.text.isNotEmpty()) {
                // Create a new user with a first and last name
                val penduduk = hashMapOf(
                    "Nomor_Identitas" to binding.nomorIdentitas.text.toString().toInt(),
                    "Nama_Penduduk" to binding.namaPenduduk.text.toString(),
                    "Usia" to binding.usiaPenduduk.text.toString().toInt(),
                    "Alamat" to binding.alamatPenduduk.text.toString()
                )

                // Add a new document with a generated ID
                db.collection("Penduduk")
                    .add(penduduk)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(applicationContext, "Data berhasil ditambahkan dengan ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
                        // Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(applicationContext, "Error: "+e.toString(), Toast.LENGTH_SHORT).show()
                        // Log.w(TAG, "Error adding document", e)
                    }
            }
            else {
                Toast.makeText(applicationContext, "Form tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show()
            }
        })

        // Fungsi Button Read Data
        binding.btnRead.setOnClickListener(View.OnClickListener {
            val data = StringBuffer()
            db.collection("Penduduk")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        data.append("Nomor Identitas : " + document.get("Nomor_Identitas") + "\n")
                        data.append("Nama Penduduk : " + document.get("Nama_Penduduk") + "\n")
                        data.append("Usia : " + document.get("Usia") + "\n")
                        data.append("Alamat : " + document.get("Alamat") + "\n" + "\n")
                    }
                    displayMessage("Informasi Penduduk", data.toString())
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, "Error: "+exception.toString(), Toast.LENGTH_SHORT).show()
                }
        })

        // Fungsi Button Delete Data
        binding.btnDelete.setOnClickListener(View.OnClickListener {
            // Hapus Data Berdasarkan Nomor Identitas
            if(binding.nomorIdentitas.text.isNotEmpty()) {
                val query = db.collection("Penduduk")
                    .whereEqualTo("Nomor_Identitas", binding.nomorIdentitas.text.toString().toInt())
                    .get()
                query.addOnSuccessListener {
                    for(document in it) {
                        db.collection("Penduduk").document(document.id).delete().addOnSuccessListener {
                            Toast.makeText(applicationContext, "Data penduduk berhasil dihapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            // Hapus Data Berdasarkan Nama Penduduk
            else if(binding.namaPenduduk.text.isNotEmpty()) {
                val query = db.collection("Penduduk")
                    .whereEqualTo("Nama_Penduduk", binding.namaPenduduk.text.toString())
                    .get()
                query.addOnSuccessListener {
                    for(document in it) {
                        db.collection("Penduduk").document(document.id).delete().addOnSuccessListener {
                            Toast.makeText(applicationContext, "Data penduduk berhasil dihapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            // Hapus Data Berdasarkan Usia
            else if(binding.usiaPenduduk.text.isNotEmpty()) {
                val query = db.collection("Penduduk")
                    .whereEqualTo("Usia", binding.usiaPenduduk.text.toString().toInt())
                    .get()
                query.addOnSuccessListener {
                    for(document in it) {
                        db.collection("Penduduk").document(document.id).delete().addOnSuccessListener {
                            Toast.makeText(applicationContext, "Data penduduk berhasil dihapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            // Hapus Data Berdasarkan Alamat Penduduk
            else if(binding.alamatPenduduk.text.isNotEmpty()) {
                val query = db.collection("Penduduk")
                    .whereEqualTo("Alamat", binding.alamatPenduduk.text.toString())
                    .get()
                query.addOnSuccessListener {
                    for(document in it) {
                        db.collection("Penduduk").document(document.id).delete().addOnSuccessListener {
                            Toast.makeText(applicationContext, "Data penduduk berhasil dihapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            else {
                Toast.makeText(applicationContext, "Masukkan nama penduduk yang ingin dihapus: ", Toast.LENGTH_SHORT).show()
            }
        })

        // Fungsi untuk Button Update Data
        binding.btnUpdate.setOnClickListener(View.OnClickListener {
            if(binding.nomorIdentitas.text.isNotEmpty() && binding.namaPenduduk.text.isNotEmpty()
                && binding.usiaPenduduk.text.isNotEmpty() && binding.alamatPenduduk.text.isNotEmpty()) {
                // Create a new user with a first and last name
                val penduduk = hashMapOf(
                    "Nomor_Identitas" to binding.nomorIdentitas.text.toString().toInt(),
                    "Nama_Penduduk" to binding.namaPenduduk.text.toString(),
                    "Usia" to binding.usiaPenduduk.text.toString().toInt(),
                    "Alamat" to binding.alamatPenduduk.text.toString()
                )

                val query = db.collection("Penduduk")
                    .whereEqualTo("Nomor_Identitas", binding.nomorIdentitas.text.toString().toInt())
                    .get()
                query.addOnSuccessListener {
                    for(document in it) {
                        db.collection("Penduduk").document(document.id).set(penduduk, SetOptions.merge())
                    }
                    Toast.makeText(applicationContext, "Data penduduk berhasil di Update", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(applicationContext, "Form tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun displayMessage(title: String, data: String) {
        val builder = AlertDialog.Builder(this@DashboardActivity)
        builder.setTitle(title)
        builder.setMessage(data)
        builder.setCancelable(true)
        builder.show()
    }

}