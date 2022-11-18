package com.example.e_kartumrt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

}