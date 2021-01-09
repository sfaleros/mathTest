package com.example.tester

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.settings_activity.*

class  settings : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings_activity)

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        supportActionBar!!.title="назад"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //if(negativeSwith.isChecked == true){ }


    }

    override fun onPause() {
        super.onPause()

        val editor = prefs.edit()

        editor.putString("settingsPassword", settingsPassword.text.toString()).apply()
        editor.putInt("task", task.text.toString().toInt()).apply()
        editor.putBoolean("negativeSwitch",negativeSwitch.isChecked ).apply()

        editor.putInt("mnTimeForExample", mnTimeForExample.text.toString().toInt()).apply()
        editor.putInt("mnExampleSum", mnExampleSum.text.toString().toInt()).apply()
        editor.putInt("mnStartNum", mnStartNum.text.toString().toInt()).apply()
        editor.putInt("mnStopNum", mnStopNum.text.toString().toInt()).apply()

        editor.putInt("dilTimeForExample", dilTimeForExample.text.toString().toInt()).apply()
        editor.putInt("dilExampleSum", dilExampleSum.text.toString().toInt()).apply()
        editor.putInt("dilStartNum", dilStartNum.text.toString().toInt()).apply()
        editor.putInt("dilStopNum", dilStopNum.text.toString().toInt()).apply()

        editor.putInt("plusTimeForExample", plusTimeForExample.text.toString().toInt()).apply()
        editor.putInt("plusExampleSum", plusExampleSum.text.toString().toInt()).apply()
        editor.putInt("plusStartNum", plusStartNum.text.toString().toInt()).apply()
        editor.putInt("plusStopNum", plusStopNum.text.toString().toInt()).apply()

        editor.putInt("minTimeForExample", minTimeForExample.text.toString().toInt()).apply()
        editor.putInt("minExampleSum", minExampleSum.text.toString().toInt()).apply()
        editor.putInt("minStartNum", minStartNum.text.toString().toInt()).apply()
        editor.putInt("minStopNum", minStopNum.text.toString().toInt()).apply()

        editor.putInt("powTimeForExample", powTimeForExample.text.toString().toInt()).apply()
        editor.putInt("powExampleSum", powExampleSum.text.toString().toInt()).apply()
        editor.putInt("powStartNum", powStartNum.text.toString().toInt()).apply()
        editor.putInt("powStopNum", powStopNum.text.toString().toInt()).apply()
        editor.putInt("powMaxPow", powMaxPow.text.toString().toInt()).apply()

        editor.putInt("sqrtTimeForExample", sqrtTimeForExample.text.toString().toInt()).apply()
        editor.putInt("sqrtExampleSum", sqrtExampleSum.text.toString().toInt()).apply()
        editor.putInt("sqrtStartNum", sqrtStartNum.text.toString().toInt()).apply()
        editor.putInt("sqrtStopNum", sqrtStopNum.text.toString().toInt()).apply()

    }


    override fun onResume() {
        super.onResume()

        settingsPassword.setText(prefs.getString("settingsPassword", ""))
        task.setText(prefs.getInt("task", 50).toString())
        negativeSwitch.isChecked = prefs.getBoolean("negativeSwitch", false)

        mnTimeForExample.setText(prefs.getInt("mnTimeForExample", 10).toString())
        mnExampleSum.setText(prefs.getInt("mnExampleSum", 10).toString())
        mnStartNum.setText(prefs.getInt("mnStartNum", 2).toString())
        mnStopNum.setText(prefs.getInt("mnStopNum", 9).toString())

        dilTimeForExample.setText(prefs.getInt("dilTimeForExample", 10).toString())
        dilExampleSum.setText(prefs.getInt("dilExampleSum", 10).toString())
        dilStartNum.setText(prefs.getInt("dilStartNum", 2).toString())
        dilStopNum.setText(prefs.getInt("dilStopNum", 9).toString())

        plusTimeForExample.setText(prefs.getInt("plusTimeForExample", 10).toString())
        plusExampleSum.setText(prefs.getInt("plusExampleSum", 10).toString())
        plusStartNum.setText(prefs.getInt("plusStartNum", 2).toString())
        plusStopNum.setText(prefs.getInt("plusStopNum", 9).toString())

        minTimeForExample.setText(prefs.getInt("minTimeForExample", 10).toString())
        minExampleSum.setText(prefs.getInt("minExampleSum", 10).toString())
        minStartNum.setText(prefs.getInt("minStartNum", 2).toString())
        minStopNum.setText(prefs.getInt("minStopNum", 9).toString())


        powTimeForExample.setText(prefs.getInt("powTimeForExample", 10).toString())
        powExampleSum.setText(prefs.getInt("powExampleSum", 10).toString())
        powStartNum.setText(prefs.getInt("powStartNum", 2).toString())
        powStopNum.setText(prefs.getInt("powStopNum", 9).toString())
        powMaxPow.setText(prefs.getInt("powMaxPow", 2).toString())

        sqrtTimeForExample.setText(prefs.getInt("sqrtTimeForExample", 10).toString())
        sqrtExampleSum.setText(prefs.getInt("sqrtExampleSum", 10).toString())
        sqrtStartNum.setText(prefs.getInt("sqrtStartNum", 2).toString())
        sqrtStopNum.setText(prefs.getInt("sqrtStopNum", 9).toString())

    }
}

/*
supportFragmentManager
.beginTransaction()
//.replace(R.id.settings, SettingsFragment())
.commit()
supportActionBar?.setDisplayHomeAsUpEnabled(true)

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
   }



 */