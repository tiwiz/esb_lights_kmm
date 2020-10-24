package com.rob.lights.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rob.lights.shared.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.rob.lights.shared.LightRepository

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val repository by lazy {
        LightRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        lifecycleScope.launchWhenResumed {
            val lights = repository.getLights()
            Log.d("TEST", lights.toString())
        }
    }
}
