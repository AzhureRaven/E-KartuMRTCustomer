package com.example.e_kartumrt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_kartumrt.Koneksi.toRupiah

class DRuteAdapter  (
    var drute: ArrayList<DRute>,
    var context: Context
) : RecyclerView.Adapter<DRuteAdapter.CustomViewHolder>(){

    //var setOnClickListener:((stasiun: Stasiun?)-> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.rv_drute, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val dr = drute[position]
        val stasiun = dr.getStasiun()
        holder.tvJudul.text = stasiun?.nama_stasiun
        holder.tvDetail.visibility = View.GONE
        holder.tvHarga.visibility = View.GONE
        holder.tvTgl.text = stasiun?.alamat
        holder.tvJarakNext.text = "${dr.jarak_next} Meter"
        when(stasiun?.status_stasiun){
            0 -> holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.terpakai))
            1 -> holder.rvMain.setBackgroundColor(context.resources.getColor(R.color.aktif))
        }
        if(position == drute.size-1) {
            holder.tvJarakNext.visibility = View.GONE
            holder.ivNext.visibility = View.GONE
        }
        /*holder.itemView.setOnClickListener{
            setOnClickListener?.invoke(stasiun)
        }*/
    }

    override fun getItemCount(): Int {
        return drute.size
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvJudul = view.findViewById<TextView>(R.id.tvJudul)
        val tvDetail = view.findViewById<TextView>(R.id.tvDetail)
        val tvTgl = view.findViewById<TextView>(R.id.tvTgl)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvJarakNext = view.findViewById<TextView>(R.id.tvJarakNext)
        val rvMain = view.findViewById<LinearLayout>(R.id.rvMain)
        val ivNext = view.findViewById<ImageView>(R.id.ivNext)
    }
}