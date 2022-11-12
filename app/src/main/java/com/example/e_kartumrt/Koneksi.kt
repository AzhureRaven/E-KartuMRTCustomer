package com.example.e_kartumrt

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.sql.Connection
import java.sql.DriverManager


object Koneksi {

    fun getConnection():Connection{
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        Class.forName("org.mariadb.jdbc.Driver")
        //Class.forName("com.mysql.jdbc.Driver");
        val ip = IP.getIP() //pake ip laptop sekarang yg ipv4, cari di cmd ipconfig //copy IP.kt di discord, paste ke project sendiri, di gitignore itu biar gk tabrakan ip address masing-masing laptop
        //xampp kemungkinan juga perlu di run as administrator untuk bekerja
        //val ip = "localhost"
        val jdbcUrl = "jdbc:mariadb://$ip:3306/e_kartu_mrt"
        try {
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
        return DriverManager.getConnection(jdbcUrl, "root", "")
    }

    //EKartu
    fun getEKartus(): ArrayList<EKartu>{

        val query = getConnection().prepareStatement("select * from e_kartu")
        val result = query.executeQuery()
        val EKartus = ArrayList<EKartu>()
        while(result.next()){
            EKartus.add(EKartu(result.getInt("id_kartu"),
                result.getString("nama_lengkap"),
                result.getString("username"),
                result.getString("password"),
                result.getString("email"),
                result.getString("tgl_lahir"),
                result.getString("kelamin"),
                result.getString("tgl_register"),
                result.getDouble("saldo"),
                result.getInt("status_kartu")
            ))
        }
        return EKartus
    }

    fun getEKartu(username:String, password:String): EKartu?{
        val query = getConnection().prepareStatement("select * from e_kartu where username = $username and password = $password")
        val result = query.executeQuery()
        var eKartu: EKartu? = null
        while(result.next()){
            eKartu = EKartu(result.getInt("id_kartu"),
                result.getString("nama_lengkap"),
                result.getString("username"),
                result.getString("password"),
                result.getString("email"),
                result.getString("tgl_lahir"),
                result.getString("kelamin"),
                result.getString("tgl_register"),
                result.getDouble("saldo"),
                result.getInt("status_kartu")
            )
        }
        return eKartu
    }

    fun insertEKartu(eKartu: EKartu){
        val query = getConnection().prepareStatement("insert into e_kartu (nama_lengkap,username,password,email,tgl_lahir,kelamin,saldo) " +
                "values ('${eKartu.nama_lengkap}','${eKartu.username}','${eKartu.password}','${eKartu.email}','${eKartu.tgl_lahir}','${eKartu.kelamin}',${eKartu.saldo})")
        val result = query.executeUpdate()
    }

}