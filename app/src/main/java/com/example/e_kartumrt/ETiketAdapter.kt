package com.example.e_kartumrt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.e_kartumrt.Koneksi.toRupiah
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ETiketAdapter (
    var eTiket: ArrayList<ETiket>,
    var context: Context
) : RecyclerView.Adapter<ETiketAdapter.CustomViewHolder>(){

    var setOnClickListener:((eTiket: ETiket)-> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.rv_main, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val tiket = eTiket[position]
        holder.tvJudul.text = Koneksi.getRute(tiket)?.nama_rute
        holder.tvDetail.text = "${Koneksi.getStasiunAwal(tiket)?.nama_stasiun}\nto\n${Koneksi.getStasiunAkhir(tiket)?.nama_stasiun}"
        holder.tvTgl.text = Koneksi.getDate(tiket.tgl_cetak,"dd MMMM yyyy HH:mm")
        holder.tvHarga.text = tiket.harga?.toInt()?.toRupiah()
        when(tiket.status_tiket){
            0 -> holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.terpakai))
            1 -> holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.aktif))
            2 -> holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.nonaktif))
            3 -> holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.digunakkan))
        }
        holder.itemView.setOnClickListener{
            setOnClickListener?.invoke(tiket)
        }
    }

    override fun getItemCount(): Int {
        return eTiket.size
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvJudul = view.findViewById<TextView>(R.id.tvJudul)
        val tvDetail = view.findViewById<TextView>(R.id.tvDetail)
        val tvTgl = view.findViewById<TextView>(R.id.tvTgl)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val rvMain = view.findViewById<LinearLayout>(R.id.rvMain)
    }
}