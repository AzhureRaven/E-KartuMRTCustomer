package com.example.e_kartumrt

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.sql.Connection
import java.sql.DriverManager


object Koneksi {

    fun getConnection():Connection{

        try {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            Class.forName("org.mariadb.jdbc.Driver")
            val ip = IP.getIP() //pake ip laptop sekarang yg ipv4, cari di cmd ipconfig //copy IP.kt di discord, paste ke project sendiri, di gitignore itu biar gk tabrakan ip address masing-masing laptop
            //val ip = "localhost"
            val jdbcUrl = "jdbc:mariadb://$ip:3306/e_kartu_mrt"
            val connection = DriverManager.getConnection(jdbcUrl, "root", "")
            println(connection.isValid(0))
            println("Bekerja")

            //briefly cara pakenya
            /*val query = connection.prepareStatement("SELECT * FROM rute")
            val res = query.execute()//general purpose, return bool kalau berhasil
            val res2 = query.executeUpdate()//INSERT UPDATE DELETE, return int
            val result = query.executeQuery()//select

            while(result.next()){

                val id = result.getInt("id_rute")

                val name = result.getString("nama_rute")
                println("$id $name")
            }*/

            return connection
        }
        catch (e:Exception){
            println(e)
        }
        return DriverManager.getConnection("", "root", "")
    }

}