package com.example.e_kartumrt

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.e_kartumrt.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainMenuBinding
    lateinit var eKartu: EKartu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        eKartu = intent.getParcelableExtra<EKartu>("ekartu") as EKartu

        binding.bnUser.setOnItemSelectedListener {
            if (it.itemId == R.id.menuProfile) {
                loadProfile()
            } else if (it.itemId == R.id.menuHome) {
                loadHome()
            }
            else if (it.itemId == R.id.menuTiket) {
                loadETiket()
            }
            return@setOnItemSelectedListener true
        }
        loadProfile()
    }

    fun loadProfile(){
        val fragment = ProfileFragment(eKartu,this)
        fragment.setOnLogoutListener = {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Apakah Anda Yakin Ingin Logout?")
            builder.setPositiveButton("Iya",DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()
                })
            builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            val alert: AlertDialog = builder.create()
            alert.show()
        }
        fragment.setOnSaldoListener = {saldo: Int ->
            Koneksi.tambahSaldo(eKartu, saldo)
            eKartu = Koneksi.getEKartu(eKartu.username,eKartu.password)!!
            Toast.makeText(this, "Berhasil Tambah Saldo!", Toast.LENGTH_SHORT).show()
            loadProfile()
        }
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flUser, fragment)
        fragmentManager.setReorderingAllowed(true)
        fragmentManager.commit()
    }

    fun loadETiket(){
        val fragment = ETiketsFragment(eKartu,this)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flUser, fragment)
        fragmentManager.setReorderingAllowed(true)
        fragmentManager.commit()
    }

    fun loadHome(){
        val fragment = HomeFragment(eKartu)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flUser, fragment)
        fragmentManager.setReorderingAllowed(true)
        fragmentManager.commit()
    }

}