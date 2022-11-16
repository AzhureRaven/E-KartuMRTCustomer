package com.example.e_kartumrt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.e_kartumrt.databinding.ActivityBeliEtiketBinding
import com.example.e_kartumrt.databinding.ActivityDetailTiketBinding
import com.example.e_kartumrt.databinding.ActivityMainMenuBinding

class BeliETiketActivity : AppCompatActivity() {
    lateinit var binding: ActivityBeliEtiketBinding
    lateinit var eKartu: EKartu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeliEtiketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        eKartu = intent.getParcelableExtra<EKartu>("ekartu") as EKartu
        loadRute()
    }

    fun loadRute(){
        val fragment = BeliETiketRuteFragment(eKartu,this)
        fragment.setOnClickListener = {rute: Rute ->
            loadTiket(rute)
        }
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flETiket, fragment)
        fragmentManager.setReorderingAllowed(true)
        fragmentManager.commit()
    }

    fun loadTiket(rute:Rute){
        val fragment = BeliETiketFragment(eKartu, rute,this)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.flETiket, fragment)
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