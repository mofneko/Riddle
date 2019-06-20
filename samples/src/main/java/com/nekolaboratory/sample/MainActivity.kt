package com.nekolaboratory.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nekolaboratory.Riddle.Riddle
import com.nekolaboratory.Riddle.RiddleCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val detectCallback = RiddleCallback {
            check_text.text = "detect"
        }
        var Riddle = Riddle()
        Riddle.initialize(3000, detectCallback)
        button.setOnClickListener {
            Riddle.start()
        }
    }
}
