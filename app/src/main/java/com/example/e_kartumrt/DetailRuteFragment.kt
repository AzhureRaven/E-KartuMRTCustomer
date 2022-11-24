package com.example.e_kartumrt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_kartumrt.Koneksi.toRupiah
import com.example.e_kartumrt.databinding.FragmentBrowseRuteBinding
import com.example.e_kartumrt.databinding.FragmentDetailRuteBinding

class DetailRuteFragment(val rute: Rute, val conte: Context) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentDetailRuteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailRuteBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    lateinit var drutes: ArrayList<DRute>
    lateinit var dRuteAdapter: DRuteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDetailRute.text = rute.nama_rute
        binding.tvDetailPPM.text = "PPM : ${rute.ppm.toInt().toRupiah()}/Meter"
        drutes = Koneksi.getDRutes(rute)
        dRuteAdapter = DRuteAdapter(drutes,conte)
        dRuteAdapter.setOnClickListener = { stasiun: Stasiun? ->

        }
        val layoutManager = LinearLayoutManager(context)
        binding.rvDetailRute.adapter = dRuteAdapter
        binding.rvDetailRute.layoutManager = layoutManager
    }
}