package com.example.e_kartumrt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_kartumrt.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnToLog.setOnClickListener{
            finish()
        }


        binding.btnReg.setOnClickListener {

            if(binding.etRegName.text.toString() != "" && binding.etRegEmail.text.toString() != "" &&
                binding.etRegUser.text.toString() != "" && (binding.rbRegL.isChecked || binding.rbRegP.isChecked) &&
                binding.etRegPass.text.toString() != ""){
                val emailPattern:Regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
                if(binding.etRegEmail.text.toString().matches(emailPattern)){
                    if(binding.etRegPass.text.toString() == binding.etRegPassConf.text.toString()){
                        val check = Koneksi.checkEKartu(binding.etRegUser.text.toString(),binding.etRegEmail.text.toString())
                        if(check == null){
                            var kel = "L"
                            if(binding.rbRegP.isChecked) kel = "P"
                            val tgl = "${binding.dpTglLahir.year}-${binding.dpTglLahir.month}-${binding.dpTglLahir.dayOfMonth} 00:00:00"
                            val ekartu = EKartu(0,binding.etRegName.text.toString(),binding.etRegUser.text.toString(),
                                binding.etRegPass.text.toString(),binding.etRegEmail.text.toString(),tgl,kel,
                                "",0.00,1)
                            Koneksi.insertEKartu(ekartu)
                            Toast.makeText(this, "Berhasil Register!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Username/Email tidak unik!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this, "Confirmation Password Salah!", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Email tidak valid!", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this, "Input Semua Data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}