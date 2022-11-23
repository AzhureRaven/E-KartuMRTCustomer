package com.example.e_kartumrt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_kartumrt.databinding.FragmentBeliETiketRuteBinding
import com.example.e_kartumrt.databinding.FragmentBrowseRuteBinding

class BrowseRuteFragment(val conte: Context) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var binding: FragmentBrowseRuteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBrowseRuteBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    var setOnClickListener:((rute: Rute)-> Unit)? = null
    lateinit var rutes: ArrayList<Rute>
    lateinit var browseRuteAdapter: BrowseRuteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRute()
        binding.ivSearch.setOnClickListener {
            loadRute()
        }
    }

    fun loadRute(){
        if(binding.etSearch.text.toString()=="") rutes = Koneksi.getRutes()
        else rutes = Koneksi.getRutes(binding.etSearch.text.toString())
        browseRuteAdapter = BrowseRuteAdapter(rutes,conte)
        browseRuteAdapter.setOnClickListener = { rute: Rute ->
            setOnClickListener?.invoke(rute)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.rvBrowseRute.adapter = browseRuteAdapter
        binding.rvBrowseRute.layoutManager = layoutManager
    }
}