package com.example.e_kartumrt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_kartumrt.databinding.FragmentBeliETiketRuteBinding
import com.example.e_kartumrt.databinding.FragmentHomeBinding

class BeliETiketRuteFragment(val eKartu: EKartu, val conte: Context) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentBeliETiketRuteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeliETiketRuteBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    var setOnClickListener:((rute: Rute)-> Unit)? = null
    lateinit var rutes: ArrayList<Rute>
    lateinit var beliRuteAdapter: BeliRuteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rutes = Koneksi.getRutes()
        beliRuteAdapter = BeliRuteAdapter(rutes,conte)
        beliRuteAdapter.setOnClickListener = { rute: Rute ->
            setOnClickListener?.invoke(rute)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.rvBeliRute.adapter = beliRuteAdapter
        binding.rvBeliRute.layoutManager = layoutManager
    }
}