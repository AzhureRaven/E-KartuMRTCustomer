package com.example.e_kartumrt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.e_kartumrt.Koneksi.toRupiah
import com.example.e_kartumrt.databinding.FragmentBeliETiketBinding
import com.example.e_kartumrt.databinding.FragmentBeliETiketRuteBinding

class BeliETiketFragment(val eKartu: EKartu, val rute: Rute, val conte: Context) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentBeliETiketBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeliETiketBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    lateinit var druteAdapter: ArrayAdapter<DRute>
    lateinit var drutes: ArrayList<DRute>
    var harga = 0
    var jarak = 0
    var awal = -1
    var akhir = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drutes = Koneksi.getDRutes(rute)
        druteAdapter = ArrayAdapter(conte,
            android.R.layout.simple_spinner_item,
            drutes)
        binding.spnAwal.adapter = druteAdapter
        binding.spnAkhir.adapter = druteAdapter
        binding.tvNamaRute.text = rute.nama_rute
        binding.tvHargaPPM.text = "${rute.ppm.toInt().toRupiah()}/Meter"
        binding.tvSaldo.text = eKartu.saldo.toInt().toRupiah()
        binding.ivBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        binding.spnAwal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                awal = p2
                binding.tvStaAwal.text = Koneksi.getStasiun(drutes[awal])?.nama_stasiun
                binding.tvAlaAwal.text = Koneksi.getStasiun(drutes[awal])?.alamat
                hitungHarga()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                awal = -1
                binding.tvStaAwal.text = ""
                binding.tvAlaAwal.text = ""
            }
        }
        binding.spnAkhir.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                akhir = p2
                binding.tvStaAkhir.text = Koneksi.getStasiun(drutes[awal])?.nama_stasiun
                binding.tvAlaAkhir.text = Koneksi.getStasiun(drutes[awal])?.alamat
                hitungHarga()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                akhir = -1
                binding.tvStaAkhir.text = ""
                binding.tvAlaAkhir.text = ""
            }
        }
        hitungHarga()
    }

    fun hitungHarga(){
        harga = 0
        jarak = 0
        if(awal > -1 && akhir > -1){
            if(awal != akhir){
                if(akhir > awal){
                    for(i in awal until akhir){
                        jarak += drutes[i].jarak_next
                    }
                }
                else{
                    for(i in akhir until awal){
                        jarak += drutes[i].jarak_next
                    }
                }
                harga = jarak * rute.ppm.toInt()
            }
        }
        binding.tvHarga.text = harga.toRupiah()
        binding.tvJarak.text = "$jarak Meter"
        if(jarak>0){
            binding.btnBeli.isEnabled = true
            binding.btnBeliSaldo.isEnabled = true
        }
        else{
            binding.btnBeli.isEnabled = false
            binding.btnBeliSaldo.isEnabled = false
        }
    }

}