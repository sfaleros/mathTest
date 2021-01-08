package com.example.tester

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_password.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar!!.title="тест"
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)

        //supportActionBar!!.title="Go Back"
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val mod = intent.getStringExtra("mod")
        //var questrun = false
        var firsrfuncounter = 0
        var middlefuntriger = false
        //var failquestions = listOf<String>()
        var res = listOf<Int>(1,1)
        var countResList : ArrayList<Int>
        var totalBall = 0
        var totalPause = 0
        val ExampleSum:Int
        ExampleSum = prefs.getInt("${mod}ExampleSum", 10)






        //menuButton.setOnClickListener {setContentView(R.layout.activity_menu)}
        //val menuIntent = Intent(this, menu::class.java)
        //menuButton.setOnClickListener{startActivity(menuIntent)}
            btn1.setOnClickListener { setTextFields("1") }
            btn2.setOnClickListener { setTextFields("2") }
            btn3.setOnClickListener { setTextFields("3") }
            btn4.setOnClickListener { setTextFields("4") }
            btn5.setOnClickListener { setTextFields("5") }
            btn6.setOnClickListener { setTextFields("6") }
            btn7.setOnClickListener { setTextFields("7") }
            btn8.setOnClickListener { setTextFields("8") }
            btn9.setOnClickListener { setTextFields("9") }
            btn0.setOnClickListener { setTextFields("0") }
            mi.setOnClickListener {
                if (answer.text.toString() == "")
                setTextFields("-")
            }




        back.setOnClickListener {
            val str = answer.text.toString()
            if (str.isNotEmpty()) {
                answer.text = str.substring(0, str.length - 1)
            }
        }

        ok.setOnClickListener {
            if (ok.text == "start") {
                resAnswer.setTextColor(Color.parseColor("#000000"))
                firsrfuncounter++
                res = ask(mod)
                answer.text = ""
                resAnswer.text = ""
                middlefuntriger = true
                ok.text = "ok"
            } else {
                    if (!middlefuntriger) {
                        ok.text = "start"
                    }else{
                        if(answer.text.toString() != "" && answer.text.toString() != "-") {

                            countResList =  count(res)

                            totalPause += countResList[0]
                            totalBall += countResList[1]

                            middlefuntriger = false

                            if(firsrfuncounter >= ExampleSum){
                                endFun(totalBall , (totalPause/ExampleSum).toInt() , ExampleSum*2)
                            }
                        }
                    }
            }

        }


    }

    
    fun setTextFields(str: String) {
        answer.append(str)
    }




    fun ask(mod: String): List<Int>{
        if (mod == "mn") {

            var int1 :Int
            var int2 :Int

            var StartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("mnStartNum",2)
            val StopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("mnStopNum", 9)
            val negativeSwitch = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("negativeSwitch", false)

            if (StopNum > StartNum) {
                int1 = (StartNum..StopNum).random()
                int2 = (StartNum..StopNum).random()
            }else {
                int1 = (StopNum..StartNum).random()
                int2 = (StopNum..StartNum).random()
            }

            if(negativeSwitch == true && (1..2).random() == 2){
               int1 = int1 * -1
            }
            if(negativeSwitch == true && (1..2).random() == 2){
                int2 = int2 * -1
            }

                val result = int1 * int2
                question.text = ("$int1 * $int2")

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 1)



        }


        if (mod == "dil") {
            var int1 :Int
            var int2 :Int


            val StartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("dilStartNum", 2)
            val StopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("dilStopNum", 9)
            val negativeSwitch = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("negativeSwitch", false)

            if (StopNum > StartNum) {
                int1 = (StartNum..StopNum).random()
                int2 = (StartNum..StopNum).random()
            }else {
                int1 = (StopNum..StartNum).random()
                int2 = (StopNum..StartNum).random()
            }


                if(negativeSwitch == true && (1..2).random() == 2){
                    int1 = int1 * -1
                }
                if(negativeSwitch == true && (1..2).random() == 2){
                    int2 = int2 * -1
                }


            val res = int1 * int2
            val result = res / int2
            question.text = ("$res : $int2")


            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 2)
        }


        if (mod == "plus") {

            var int1 :Int
            var int2 :Int

            val StartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("plusStartNum", 2)
            val StopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("plusStopNum", 9)
            val negativeSwitch = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("negativeSwitch", false)

            if (StopNum > StartNum) {
                int1 = (StartNum..StopNum).random()
                int2 = (StartNum..StopNum).random()
            }else {
                int1 = (StopNum..StartNum).random()
                int2 = (StopNum..StartNum).random()
            }


                if(negativeSwitch == true && (1..2).random() == 2){
                    int1 = int1 * -1
                }
                if(negativeSwitch == true && (1..2).random() == 2){
                    int2 = int2 * -1
                }


            val result = int1 + int2
            question.text = ("$int1 + $int2")

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 3)
        }


        if (mod == "min") {

            var int1 :Int
            var int2 :Int

            val StartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("minStartNum", 2)
            val StopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("minStopNum", 9)
            val negativeSwitch = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("negativeSwitch", false)

            if (StopNum > StartNum) {
                int1 = (StartNum..StopNum).random()
                int2 = (StartNum..StopNum).random()
            }else {
                int1 = (StopNum..StartNum).random()
                int2 = (StopNum..StartNum).random()
            }


            if(negativeSwitch == true && (1..2).random() == 2){
                int1 = int1 * -1
            }
            if(negativeSwitch == true && (1..3).random() == 2){
                int2 = int2 * -1
            }

            val res = int1 + int2
            val result = res - int2
            question.text = ("$res - $int2")

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 4)
        }


        if (mod == "pow") {
            var int1 :Int
            var StartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("powStartNum", 2)
            val StopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("powStopNum", 9)
            val pow = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("powMaxPow", 2)
            val negativeSwitch = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("negativeSwitch", false)


            if (StartNum < StopNum) {
                int1 = (StartNum..StopNum).random()
            }else {
                int1 = (StopNum..StartNum).random()
            }

            var int2 = (2..pow).random()



            if(negativeSwitch == true && (1..2).random() == 2){
                int1 = int1 * -1
            }


            val result =  int1.toDouble().pow(int2.toDouble()).toInt()

            if(int2 == 2){
                question.text = ("$int1²")
            }else {
                question.text = ("$int1^$int2")
            }

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 5)



        }




        if (mod == "sqrt") {

            var int1 :Int
            var StartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("sqrtStartNum", 2)
            val StopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("sqrtStopNum", 9)

            if (StartNum < StopNum) {
                int1 = (StartNum..StopNum).random()
            }else {
                int1 = (StopNum..StartNum).random()
            }

            val int2 = int1*int1
            val result = int1

            question.text = ("√$int2")

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 6)



        }






        return listOf(1,1)
    }





    fun count(result: List<Int>): ArrayList<Int>{
        val funMod = getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("funMod", false)
        var answerr  = answer.text.toString()
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())
        val endTime = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()
        val startTime = result[1]
        var TimeForExample = 1
        var soundF : Int
        var soundTi : Int
        var soundTr : Int

        if(!funMod){
            soundF = R.raw.failc
            soundTi = R.raw.timeout
            soundTr = R.raw.truec
        }else{
            soundF = R.raw.fart_1
            soundTi = R.raw.ayaya
            soundTr = R.raw.duck
        }



        var sound = R.raw.timeout



        when(result[2]){
            1 -> TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("mnTimeForExample", 10)
            2 -> TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("dilTimeForExample", 10)
            3 -> TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("plusTimeForExample", 10)
            4 -> TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("minTimeForExample", 10)
            5 -> TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("powTimeForExample", 10)
            6 -> TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("sqrtTimeForExample", 10)
        }





        if (answerr.toInt() == result[0] && endTime - startTime <= TimeForExample) {

            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this , soundTr)
            mediaPlayer?.start() // no need to call prepare(); create() does that for you


            resAnswer.setTextColor(Color.parseColor("#09ff00"))

            totalform.setBackgroundColor(Color.parseColor("#63ff69"))
            android.os.Handler().postDelayed({
                totalform.setBackgroundColor(Color.parseColor("#ffffff"))  }, 100)


            resAnswer.setText("правильно")
            ok.text = "start"

            return arrayListOf<Int>(endTime-startTime.toInt(),2)

        } else {


            if(answerr.toInt() != result[0]){
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(this , soundF)
                mediaPlayer?.start() // no need to call prepare(); create() does that for you

                question.append(" = ${result[0]}")

                totalform.setBackgroundColor(Color.parseColor("#ff7d7d"))
                android.os.Handler().postDelayed({
                    totalform.setBackgroundColor(Color.parseColor("#ffffff"))  }, 100)

                resAnswer.text = "неправильно"
                resAnswer.setTextColor(Color.parseColor("#ff0000"))
                ok.text = "start"
                return arrayListOf<Int>(endTime-startTime.toInt(),0)


            }else {

                var mediaPlayer: MediaPlayer? = MediaPlayer.create(this , soundTi)
                mediaPlayer?.start() // no need to call prepare(); create() does that for you

                if(endTime - startTime >= TimeForExample && answerr.toInt() == result[0]) {

                    totalform.setBackgroundColor(Color.parseColor("#fdff7d"))
                    android.os.Handler().postDelayed({
                        totalform.setBackgroundColor(Color.parseColor("#ffffff"))  }, 100)

                    resAnswer.text = "час вийшов"
                    resAnswer.setTextColor(Color.parseColor("#d9c007"))
                    ok.text = "start"
                    return arrayListOf<Int>(endTime-startTime.toInt(),1)
                }
                return arrayListOf<Int>(0,0)
            }

        }


    }



    fun endFun(ball : Int ,pause : Int , maxBall : Int ){
        val menuIntent = Intent(this, menu::class.java)

        val a = arrayOf<Int>(ball , maxBall , pause)
        menuIntent.putExtra("res", a);
        startActivity(menuIntent)
    }


    override fun onBackPressed() {

        if(question.text.toString() != "") {
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("вихід з тесту")
                .setMessage("ви впевнені, що бажаєте вийти ?\nвесь прогрес буде втрачено")
                .setPositiveButton(
                    "так"
                ) { dialog, which -> finish() }
                .setNegativeButton("ні", null)
                .show()

        }else{
            finish()
        }
    }

}



/*
class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выбор есть всегда")
                .setMessage("Выбери пищу")
                .setCancelable(true)
                .setPositiveButton("Вкусная пища") { dialog, id ->
                    Toast.makeText(activity, "Вы сделали правильный выбор",
                        Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Здоровая пища",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(activity, "Возможно вы правы",
                            Toast.LENGTH_LONG).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}



        if(result[2] == 1 ){
            TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("mnTimeForExample", 0)
        }else {
            if (result[2] == 2) {
                TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("dilTimeForExample", 0)
            } else {
                if (result[2] == 3) {
                    TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("plusTimeForExample", 0)
                } else {
                    if (result[2] == 4) {
                        TimeForExample = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("minTimeForExample", 0)
                    }

                }
            }
        }





    fun timesignaller(){}

                //val resultstring = result.toString()
            //var time = java.util.Calendar.getInstance("")
            //question.text = time.toString()
            //val startTime = System.currentTimeSecond()
            //Timer().schedule(1000){
             //   timesignaller()
            //}

        //var d = ask(currentDate)
       // val m = LocalDateTime.parse(currentDate)
        //val timtest: Long = currentDate1 currentDate
        //var d = ask(currentDate)
       // val m = LocalDateTime.parse(currentDate)
        //val timtest: Long = currentDate1 currentDate


//question.text =      //"$currentDate      $allsecouds   "//minutes//("$currentDate fff ${currentDate[1]}")

            val mnStartNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("mnStartNum", 0)
            val mnStopNum = getSharedPreferences("settings", Context.MODE_PRIVATE).getInt("mnStopNum", 0)
            val int1 = (mnStartNum..mnStopNum).random()
            val int2 = (mnStartNum..mnStopNum).random()
            val result = int1 * int2
            question.text = ("$int1 * $int2")

            val sdf = SimpleDateFormat("hh:mm:ss")
            val currentDate = sdf.format(Date())
            val allsecouds = currentDate.substring(0, 2).toInt()*3600+  currentDate.substring(3, 5).toInt()*60+  currentDate.substring(6, 8).toInt()

            return listOf(result, allsecouds , 1)








val set: Set<String> = HashSet()
editor.putStringSet("sins", set).apply()




val catnames: Set<String> = HashSet()
catnames.add("Мурзик")
catnames.add("Барсик")
catnames.add("Рыжик")
editor.putStringSet("strSetKey", catnames)
editor.apply()


val ret: Set<String> = prefs.getStringSet("strSetKey", HashSet<String>())
for (r in ret) {
    Log.i("Share", "Имя кота: $r")
}












            var result = 0
            val savedSins : ArrayList<String> = Gson().fromJson(prefs.getString("sins",null), object : TypeToken<ArrayList<String>>(){}.type)
            savedSins.remove("-")

            if((1..5).random() == 2 && savedSins.count() != 0){ //if((1..5).random() == 2)
                    val savedSin = savedSins.last()
                    val editor = prefs.edit()
                    savedSins.removeAt(savedSins.size-1)

                    val gson = Gson()
                    val json = gson.toJson(savedSins)
                    editor.putString("sins",json).apply()


                    val sinValList = savedSin.split("*")
                    question.text = savedSin

                    val int1 =  sinValList[0].replace(" ", "").toInt()
                    val int2 =  sinValList[1].replace(" ", "").toInt()
                    result = int1 * int2



            }else{







        val editor = prefs.edit()

        if (mod == "mn"){
            val oldSins  : ArrayList<String> = Gson().fromJson(prefs.getString("sins",null), object : TypeToken<ArrayList<String>>(){}.type)
            oldSins.addAll(sins)

            val set: Set<String> = HashSet(oldSins)
            oldSins.clear()
            oldSins.addAll(set)

            val gson = Gson()
            val json = gson.toJson(oldSins)
            editor.putString("sins",json).apply()


                //val gson1 = Gson()
                //val json1 = prefs.getString("sins",null)
                //val type = object : TypeToken<ArrayList<String>>(){}.type


                //val set1 = prefs.getStringSet("sins", null)
                val s1  : ArrayList<String> = Gson().fromJson(prefs.getString("sins",null), object : TypeToken<ArrayList<String>>(){}.type)
                question.text = s1.toString()

        }




        <ProgressBar
            android:id="@+id/progressBar"
            style="?android:attr/progressBarStyleHorizontal"
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:layout_weight="1" />





















*/
