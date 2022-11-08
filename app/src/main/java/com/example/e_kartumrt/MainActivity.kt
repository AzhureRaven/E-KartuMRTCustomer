package com.example.e_kartumrt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val query = Koneksi.getConnection().prepareStatement("select * from e_kartu") //gara-gara bisa return null, harus dikasih ?
        val result = query.executeQuery()
        val users = ArrayList<User>()
        while(result.next()){
            users.add(User(result.getInt("id_kartu"),
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
        Toast.makeText(this, users.toString(), Toast.LENGTH_SHORT).show()
        println(users.toString())
    }
}