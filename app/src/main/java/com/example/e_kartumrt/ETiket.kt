package com.example.e_kartumrt

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ETiket(
    var id_tiket:Int,
    var id_kartu:Int,
    var id_stasiun_awal:Int,
    var id_stasiun_akhir:Int?,
    var id_rute:Int,
    var harga:Double?,
    var tgl_cetak:String,
    var tgl_masuk:String?,
    var tgl_keluar:String?,
    var mode_tiket:Int,
    var status_tiket:Int
) : Parcelable{
}