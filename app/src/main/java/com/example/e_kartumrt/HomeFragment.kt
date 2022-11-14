package com.example.e_kartumrt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_kartumrt.databinding.FragmentETiketsBinding
import com.example.e_kartumrt.databinding.FragmentHomeBinding

class HomeFragment(val eKartu: EKartu) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBeliETiket.setOnClickListener {
            val intent = Intent(context, BeliETiketActivity::class.java)
            intent.putExtra("ekartu",eKartu)
            startActivity(intent)
        }
        binding.btnBrowseRute.setOnClickListener {
            val intent = Intent(context, BrowseRuteActivity::class.java)
            startActivity(intent)
        }
    }

}