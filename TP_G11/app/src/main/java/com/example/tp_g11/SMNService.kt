package com.example.tp_g11

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.google.gson.Gson

val gson = Gson()

val retrofit = Retrofit.Builder()
    .baseUrl("https://ws.smn.gob.ar/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()


interface SMNService {
    @GET("map_items/weather")
    fun getClima(): Call<List<Base>>


}

object SMNApi {

    val apiService = retrofit.create(SMNService::class.java)

    fun getClima() {
        val call = apiService.getClima()

        call.enqueue(object : Callback<List<Base>> {
            override fun onResponse(call: Call<List<Base>>, response: Response<List<Base>>) {
                if (response.isSuccessful) {
                    // repuesta exitosa
                    val clima: List<Base>? = response.body()
                    if (clima != null) {

                        Log.d("API Response", clima.toString())

                    }
                } else {
                    // error

                }
            }

            override fun onFailure(call: Call<List<Base>>, t: Throwable) {
                // Handle the network failure here
                Log.e("API Error", "Failed to retrieve weather data: ${t.message}")
            }
        })
    }
}