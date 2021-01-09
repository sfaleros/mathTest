package com.example.tester

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.getSettings
import java.text.SimpleDateFormat
import java.util.*

class menu : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val taskBall = prefs.getInt("task", 50)
        var mod = "none"
        val day = SimpleDateFormat("d").format(Date())
        val maybeDay= prefs.getString("day", "1")
        val PasswordIntent = Intent(this, password::class.java)

        fun setInstallTime(){
            val sdf = SimpleDateFormat("d MMM yyyy HH:mm")
            val currentDate = sdf.format(Date())
            editor.putString("startData", currentDate.toString()).apply()
        }

        installTime.setText("встановлено: " + prefs.getString("startData", ""))


        if(day !=  maybeDay){
            editor.putString("day", day).apply()
            editor.putInt("dayBall", 0).apply()
        }
        var dayBall = prefs.getInt("dayBall", 0)
        taskp.text = "сьогодні зароблено $dayBall балів з $taskBall потрібних"

        if(installTime.text =="встановлено: "){
            setInstallTime()
        }


        getSettings.setOnClickListener{


            if(getSharedPreferences("settings", Context.MODE_PRIVATE).getString("settingsPassword", "12345") == ""){
                startActivity(Intent(this, settings::class.java))
            }else {
                startActivity(PasswordIntent)
            }
        }


        try{
            val res : Array<Int> = intent.extras!!["res"] as Array<Int>


            var timeName = ""
            if(res[2] in 2..4){
                timeName = "секунди"
            }else{
                if(res[2] > 4 || res[2] == 0) {
                    timeName = "секунд"
                }else{
                    if(res[2] == 1 ){
                        timeName = "секунда"
                    }
                }
            }
            dayBall += res[0]
            editor.putInt("dayBall", dayBall).apply()
            taskp.text = "сьогодні зароблено $dayBall балів з $taskBall потрібних"
            resText.text = "${res[0]} балів з ${res[1]} можливих\nсередня затримка: ${res[2]} $timeName"
        }catch (e: KotlinNullPointerException){
            resText.text = ""
        }





        fun buttonLight(){
            mn.setBackgroundColor(Color.parseColor("#008236"))
            dil.setBackgroundColor(Color.parseColor("#008236"))
            plus.setBackgroundColor(Color.parseColor("#008236"))
            min.setBackgroundColor(Color.parseColor("#008236"))
            pow.setBackgroundColor(Color.parseColor("#008236"))
            sqrt.setBackgroundColor(Color.parseColor("#008236"))
        }



        mn.setOnClickListener {
            buttonLight()
            mn.setBackgroundColor(Color.parseColor("#00ffbf"))
            mod = "mn"
        }
        dil.setOnClickListener {
            buttonLight()
            dil.setBackgroundColor(Color.parseColor("#00ffbf"))
            mod = "dil"
        }
        plus.setOnClickListener {
            buttonLight()
            plus.setBackgroundColor(Color.parseColor("#00ffbf"))
            mod = "plus"
        }
        min.setOnClickListener {
            buttonLight()
            min.setBackgroundColor(Color.parseColor("#00ffbf"))
            mod = "min"
        }

        pow.setOnClickListener {
            buttonLight()
            pow.setBackgroundColor(Color.parseColor("#00ffbf"))
            mod = "pow"
        }

        sqrt.setOnClickListener {
            buttonLight()
            sqrt.setBackgroundColor(Color.parseColor("#00ffbf"))
            mod = "sqrt"
        }



        start.setOnClickListener{
            if(mod != "none"){
                val toTastIntent = Intent(this, MainActivity::class.java).apply { putExtra("mod", mod) }
                startActivity(toTastIntent)
            }else{
                mn.setBackgroundColor(Color.parseColor("#db7474"))
                dil.setBackgroundColor(Color.parseColor("#db7474"))
                plus.setBackgroundColor(Color.parseColor("#db7474"))
                min.setBackgroundColor(Color.parseColor("#db7474"))
                pow.setBackgroundColor(Color.parseColor("#db7474"))
                sqrt.setBackgroundColor(Color.parseColor("#db7474"))

                android.os.Handler().postDelayed({

                    mn.setBackgroundColor(Color.parseColor("#008236"))
                    dil.setBackgroundColor(Color.parseColor("#008236"))
                    plus.setBackgroundColor(Color.parseColor("#008236"))
                    min.setBackgroundColor(Color.parseColor("#008236"))
                    pow.setBackgroundColor(Color.parseColor("#008236"))
                    sqrt.setBackgroundColor(Color.parseColor("#008236"))}, 150)
            }
        }


    }

}
















