package com.example.retrofitlessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitlessons.databinding.ActivityMainBinding
import com.example.retrofitlessons.retrofit.AuthRequest
import com.example.retrofitlessons.retrofit.MainApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val mainApi = retrofit.create(MainApi::class.java)

        binding.btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user = mainApi.auth(
                    AuthRequest(
                        binding.login.text.toString(),
                        binding.password.text.toString()
                    )
                )

                runOnUiThread {
                    binding.apply {
                        Picasso.get().load(user.image).into(iv)
                        firstName.text = user.firstName
                        lastName.text = user.lastName
                    }
                }
            }
        }
    }
}