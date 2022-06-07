package com.example.flowpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowpi.fragment.PiFragment
import com.example.flowpi.fragment.TimeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction().replace(R.id.place_holder, PiFragment.newInstance())
            .commit()
        supportFragmentManager
            .beginTransaction().replace(R.id.place_holder_time, TimeFragment.newInstance())
            .commit()


    }
}