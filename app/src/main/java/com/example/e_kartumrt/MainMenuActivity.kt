package com.example.e_kartumrt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_kartumrt.databinding.ActivityMainBinding
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

            }
            else if (it.itemId == R.id.menuTiket) {

            }
            return@setOnItemSelectedListener true
        }
        loadProfile()
    }

    fun loadProfile(){
        val fragment = ProfileFragment(eKartu)
        fragment.setOnLogoutListener = {
            finish()
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


}