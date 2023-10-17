package com.example.tp_g11

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.http.Path
import com.google.gson.reflect.TypeToken
import retrofit2.http.Query

val gson = Gson()

val retrofit = Retrofit.Builder()
    .baseUrl("https://ws.smn.gob.ar/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()


interface SMNService {
    @GET("map_items/weather")
    fun getClima(): Call<List<Base>>
    @GET("/forecast/{dia}")
    fun getPronostico(@Path("dia") dia: Int, @Query("id") id: Int): Call<ResponseBody>


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

    fun getPronostico(dia: Int, id: Int) {
        val call = apiService.getPronostico(dia, id)

        call.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    // repuesta exitosa
                    val jsonString = response.body()?.string()
                    if (jsonString != null) {


                        val type = object : TypeToken<List<Base>>() {}.type
                        val listaTemp : List<Base> = gson.fromJson(jsonString, type)


                        val filtrados = listaTemp?.filter {it.fid == id}



                        Log.i("DATOS", jsonString.toString())
                    }
                } else {
                    // error

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Handle the network failure here
                Log.e("API Error", "Failed to retrieve pronostico!: ${t.message}")
            }
        })
    }

    }