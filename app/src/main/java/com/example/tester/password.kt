package com.example.tester

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_password.*

class password : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        supportActionBar!!.title="назад"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val pass = getSharedPreferences("settings", Context.MODE_PRIVATE).getString("settingsPassword", "12345")
        var counter = 5



        enterPassword.setOnClickListener{

            if(passwordText.text.toString() == "funmod"){
                val funMod = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("funMod", false)
                val editor = prefs.edit()
                editor.putBoolean("funMod",!funMod).apply()
                textAboutPassword.setText("режим активовано")
            }else{

            if(passwordText.text.toString() == pass){
                val randomIntent = Intent(this, settings::class.java)
                startActivity(randomIntent)

            }else{
                passwordText.setText("")
                counter -= 1
                textAboutPassword.setText("помилка , залишилось $counter спроб ")
                if(counter == 0){
                    totalForm.setBackgroundColor(Color.parseColor("#ffa1a1"))
                    android.os.Handler().postDelayed({onBackPressed()}, 1000)

                }
            }

            }
    }

    }
}