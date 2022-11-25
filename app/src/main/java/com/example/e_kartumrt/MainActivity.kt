package com.example.e_kartumrt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_kartumrt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Koneksi.startConnection()
        //Toast.makeText(this, Koneksi.getEKartus().toString(), Toast.LENGTH_SHORT).show()

        binding.btnLogin.setOnClickListener { 
            if(binding.etLogUser.text.toString()!="" && binding.etLogPass.text.toString()!=""){
                if(binding.etLogUser.text.toString().length <= 50 && binding.etLogPass.text.toString().length <= 50){
                    val ekartu = Koneksi.getEKartu(binding.etLogUser.text.toString(),binding.etLogPass.text.toString())
                    if(ekartu != null){
                        //Toast.makeText(this, ekartu.toString(), Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainMenuActivity::class.java)
                        intent.putExtra("ekartu",ekartu)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Username/Password Invalid!", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Input tidak boleh lebih dari 50 karakter!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Masukkan Username/Password!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etLogUser.setText("")
        binding.etLogPass.setText("")
    }
}