package com.example.demoproduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView= findViewById(R.id.viewRecycler)

// code for buiding retrofit and connecting it with the api

        val retrofitbuilder =  Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitbuilder.getproductdata()// getting the data from the api

// code for handling the data. calling enqueue method to enqueue the call
// which contains two method onResponse & onFailure

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                var responseBody = p1.body()
                val Product = responseBody?.products!! // getting the product list from the response body

                productAdapter  = ProductAdapter(this@MainActivity,Product)
                recyclerView.adapter= productAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(p0: Call<MyData?>, f: Throwable) {
                Log.d("MainActivity", "onFailure: "+f.message)
            }

        })

    }
}