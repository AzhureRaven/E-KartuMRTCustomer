package com.example.e_kartumrt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.e_kartumrt.databinding.ActivityBeliEtiketBinding
import com.example.e_kartumrt.databinding.ActivityBrowseRuteBinding

class BrowseRuteActivity : AppCompatActivity() {
    lateinit var binding: ActivityBrowseRuteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowseRuteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadRute()
    }

    fun loadRute(){
        val fragment = BrowseRuteFragment(this)
        fragment.setOnClickListener = {rute: Rute ->
            loadDRute(rute)
        }
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flRute, fragment)
        fragmentManager.setReorderingAllowed(true)
        fragmentManager.commit()
    }

    fun loadDRute(rute:Rute){
        val fragment = DetailRuteFragment(rute,this)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flRute, fragment)
        fragmentManager.addToBackStack(null)
        fragmentManager.setReorderingAllowed(true)
        fragmentManager.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.back_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.optBack -> {
                finish()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}