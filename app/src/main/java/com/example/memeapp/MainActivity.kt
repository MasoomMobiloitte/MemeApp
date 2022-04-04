package com.example.memeapp

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request.Method.GET
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.memeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fetchMemes()

        binding.btnShare.setOnClickListener {
            fetchMemes()
        }
    }

    private fun fetchMemes(){
        val queue = Volley.newRequestQueue(this)

        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(GET, url , null , {
            try{
                binding.txTitle.text = it.getString("title")
                Glide.with(this).load(it.getString("url")).into(binding.image)
            }catch (e : Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })

        queue.add(jsonObjectRequest)
    }
}