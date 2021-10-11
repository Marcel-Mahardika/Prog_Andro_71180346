// I Nyoman Marcel Mahardika (71180346)

package com.example.tugasfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentB: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.b_fragment, container, false)
        val btnB = v.findViewById<Button>(R.id.btnFrgB)

        //pindah dari Fragment ke Activity
        btnB.setOnClickListener {
            //Toast untuk menampilkan pesan halaman ketika baru dibuka
            Toast.makeText(context, "Ini Halaman Tiga", Toast.LENGTH_LONG).show()

            val intent = Intent(getActivity(), HalamanTigaActivity::class.java)
            startActivity(intent)


        }
        return v
    }
}