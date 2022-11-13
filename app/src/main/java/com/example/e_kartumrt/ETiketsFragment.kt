package com.example.e_kartumrt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_kartumrt.databinding.FragmentETiketsBinding
import com.example.e_kartumrt.databinding.FragmentProfileBinding


class ETiketsFragment(val eKartu: EKartu, val conte: Context) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentETiketsBinding
    lateinit var eTikets: ArrayList<ETiket>
    lateinit var eTiketAdapter: ETiketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentETiketsBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eTikets = Koneksi.getETikets(eKartu)
        eTiketAdapter = ETiketAdapter(eTikets,conte)
        eTiketAdapter.setOnClickListener = { eTiket: ETiket ->
            val intent = Intent(context, DetailTiketActivity::class.java)
            intent.putExtra("etiket",eTiket)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.rvETiket.adapter = eTiketAdapter
        binding.rvETiket.layoutManager = layoutManager
    }

}