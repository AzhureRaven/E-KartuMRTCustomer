package com.example.e_kartumrt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_kartumrt.Koneksi.toRupiah

class BrowseRuteAdapter  (
    var rute: ArrayList<Rute>,
    var context: Context
) : RecyclerView.Adapter<BrowseRuteAdapter.CustomViewHolder>(){

    var setOnClickListener:((rute: Rute)-> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.rv_main, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val rt = rute[position]
        holder.tvJudul.text = rt.nama_rute
        holder.tvDetail.visibility = View.GONE
        holder.tvHarga.text = "${rt.ppm.toInt().toRupiah()}/Meter"
        when(rt.status_rute){
            0 -> {
                holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.terpakai))
            }
            1 -> {
                holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.aktif))
            }
        }
        holder.itemView.setOnClickListener{
            setOnClickListener?.invoke(rt)
        }
    }

    override fun getItemCount(): Int {
        return rute.size
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvJudul = view.findViewById<TextView>(R.id.tvJudul)
        val tvDetail = view.findViewById<TextView>(R.id.tvDetail)
        val tvTgl = view.findViewById<TextView>(R.id.tvTgl)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val rvMain = view.findViewById<LinearLayout>(R.id.rvMain)
    }
}