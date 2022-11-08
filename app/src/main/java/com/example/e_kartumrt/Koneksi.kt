package com.example.e_kartumrt

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.sql.DriverManager


object Koneksi {

    fun getConnection(){

        try {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            Class.forName("org.mariadb.jdbc.Driver")
            val ip = "192.168.100.182" //pake ip laptop sekarang yg ipv4, cari di cmd ipconfig
            //val ip = "localhost"
            val jdbcUrl = "jdbc:mariadb://$ip:3306/e_kartu_mrt"
            val connection = DriverManager.getConnection(jdbcUrl, "root", "")
            println(connection.isValid(0))
            println("Bekerja")

            val query = connection.prepareStatement("SELECT * FROM rute")
            val result = query.executeQuery()

            while(result.next()){

                val id = result.getInt("id_rute")

                val name = result.getString("nama_rute")
                println("$id $name")
            }
        }
        catch (e:Exception){
            println(e)
        }
    }

}