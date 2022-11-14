package com.example.e_kartumrt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.e_kartumrt.Koneksi.toRupiah
import com.example.e_kartumrt.databinding.ActivityDetailTiketBinding
import com.example.e_kartumrt.databinding.ActivityMainBinding
import com.example.e_kartumrt.databinding.ActivityMainMenuBinding

class DetailTiketActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailTiketBinding
    lateinit var eTiket: ETiket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTiketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        eTiket = intent.getParcelableExtra<ETiket>("etiket") as ETiket
        when(eTiket.status_tiket){
            0 -> {
                binding.tvETiketStatus.setBackgroundColor(this.resources.getColor(R.color.terpakai))
                binding.tvETiketStatus.text = "Status: Terpakai"
            }
            1 -> {
                binding.tvETiketStatus.setBackgroundColor(this.resources.getColor(R.color.aktif))
                binding.tvETiketStatus.text = "Status: Aktif"
            }
            2 -> {
                binding.tvETiketStatus.setBackgroundColor(this.resources.getColor(R.color.nonaktif))
                binding.tvETiketStatus.text = "Status: Non-Aktif"
            }
        }
        binding.tvETiketNo.text = "${eTiket.id_tiket}"
        binding.tvETiketRute.text = "${Koneksi.getRute(eTiket)?.nama_rute}"
        binding.tvETiketStaAwal.text = "${Koneksi.getStasiunAwal(eTiket)?.nama_stasiun}"
        binding.tvETiketAlaAwal.text = "${Koneksi.getStasiunAwal(eTiket)?.alamat}"
        binding.tvETiketStaAkhir.text = "${Koneksi.getStasiunAkhir(eTiket)?.nama_stasiun}"
        binding.tvETiketAlaAkhir.text = "${Koneksi.getStasiunAkhir(eTiket)?.alamat}"
        binding.tvETiketHarga.text = "${eTiket.harga.toInt().toRupiah()}"
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