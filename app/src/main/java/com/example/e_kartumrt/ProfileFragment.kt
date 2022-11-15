package com.example.e_kartumrt

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.Point
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.e_kartumrt.Koneksi.toRupiah
import com.example.e_kartumrt.databinding.FragmentProfileBinding



class ProfileFragment(val eKartu: EKartu, val conte:Context) : Fragment() {

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
        binding.tvProfileUsername.text = "${eKartu.username}"
        binding.tvProfileEmail.text = "${eKartu.email}"
        binding.tvProfileTglLahir.text = "${Koneksi.getDate(eKartu.tgl_lahir)}"
        binding.tvProfileTglReg.text = "${Koneksi.getDate(eKartu.tgl_register)}"
        val kelamin = if(eKartu.kelamin == "L") "Laki-Laki" else "Perempuan"
        binding.tvProfileKelamin.text = "$kelamin"
        binding.tvProfileSaldo.text = "${eKartu.saldo.toInt().toRupiah()}"

        binding.btnLogout.setOnClickListener {
            setOnLogoutListener?.invoke()
        }

        binding.btnTambahSaldo.setOnClickListener {
            if(binding.etProfileSaldo.text.toString() != ""){
                setOnSaldoListener?.invoke(binding.etProfileSaldo.text.toString().toInt())
            }
        }
        generateQR()
    }

    fun generateQR(){
        val windowManager: WindowManager = conte.getSystemService(WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val point: Point = Point()
        display.getSize(point)
        val width = point.x
        val height = point.y
        var dimen = if (width < height) width else height
        dimen = dimen * 3 / 4
        val qrEncoder = QRGEncoder("{'id':${eKartu.id_kartu},'mode':1}", null, QRGContents.Type.TEXT, dimen)
        try {
            val bitmap = Koneksi.invertImage(qrEncoder.bitmap)
            binding.ivProfileQR.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}