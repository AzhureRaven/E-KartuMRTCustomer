package com.example.e_kartumrt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_kartumrt.Koneksi.toRupiah
import com.example.e_kartumrt.databinding.FragmentProfileBinding


class ProfileFragment(val eKartu: EKartu) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentProfileBinding
    var setOnSaldoListener:((saldo:Int)-> Unit)? = null
    var setOnLogoutListener:(()-> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvProfileName.text = eKartu.nama_lengkap
        binding.tvProfileUsername.text = "${binding.tvProfileUsername.text} ${eKartu.username}"
        binding.tvProfileEmail.text = "${binding.tvProfileEmail.text} ${eKartu.email}"
        binding.tvProfileTglLahir.text = "${binding.tvProfileTglLahir.text} ${Koneksi.getDate(eKartu.tgl_lahir)}"
        binding.tvProfileTglReg.text = "${binding.tvProfileTglReg.text} ${Koneksi.getDate(eKartu.tgl_register)}"
        val kelamin = if(eKartu.kelamin == "L") "Laki-Laki" else "Perempuan"
        binding.tvProfileKelamin.text = "${binding.tvProfileKelamin.text} $kelamin"
        binding.tvProfileSaldo.text = "${binding.tvProfileSaldo.text} ${eKartu.saldo.toInt().toRupiah()}"

        binding.btnLogout.setOnClickListener {
            setOnLogoutListener?.invoke()
        }

        binding.btnTambahSaldo.setOnClickListener {
            if(binding.etProfileSaldo.text.toString() != ""){
                setOnSaldoListener?.invoke(binding.etProfileSaldo.text.toString().toInt())
            }
        }
    }
}