package com.example.tp_g11

data class Pronostico(
   val day : Int,
   val morning_temp : Int,
   val morning_id : Int,
   val morning_desc : String,
   val afternoon_temp : Int,
   val afternoon_id : Int,
   val afternoon_desc : String
)
