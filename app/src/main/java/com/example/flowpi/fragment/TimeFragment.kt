package com.example.flowpi.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import com.example.flowpi.R
import com.example.flowpi.databinding.FragmentTimeBinding
import java.math.BigDecimal
import java.util.*


class TimeFragment : Fragment() {
    lateinit var binding: FragmentTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeBinding.inflate(inflater, container,false)

        binding.chronometer.setFormat("Time: %s")
        binding.chronometer.setBase(SystemClock.elapsedRealtime())
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        binding.chronometer.setOnChronometerTickListener(Chronometer.OnChronometerTickListener { chronometer ->
            if (SystemClock.elapsedRealtime() - chronometer.base >= 20000) {
                binding.notificationBackground.setBackgroundColor(color)
            }
        })
        binding.PlayTime.setOnClickListener {
            binding.chronometer.start()
        }
        binding.PauseTime.setOnClickListener {
            binding.chronometer.stop()
        }
        binding.ResetTime.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.chronometer.start()
    }

    override fun onStop() {
        super.onStop()
        binding.chronometer.stop()
    }

    companion object {

        @JvmStatic
        fun newInstance() =TimeFragment()
    }
}