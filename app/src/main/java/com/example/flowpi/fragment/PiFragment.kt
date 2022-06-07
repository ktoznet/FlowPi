package com.example.flowpi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flowpi.databinding.FragmentPiBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class PiFragment : Fragment() {
    lateinit var binding: FragmentPiBinding
    private var const: Double = 4.0
    private var x: Int = 1
    private var y = BigDecimal(4)
    private var count: Long = 0
    private var z = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPiBinding.inflate(layoutInflater, container, false)




        GlobalScope.launch {
            val numberFlow = getNumbers()
            numberFlow.collect{n -> binding.tvOne.text = n}
        }
        return binding.root
    }

    fun getNumbers() = flow{
        delay(400L)
        while (true) {
            if (count % 2 == 0.toLong()) {
                y -= BigDecimal(const).divide(BigDecimal(x + 2), 300, 3)
                z = y.toString()
                x += 2
            } else {
                y += BigDecimal(const).divide(BigDecimal(x + 2), 300, 3)
                z = y.toString()
                x += 2
            }
            if (x % 1000000 == 1) {

                Log.d("mylog", z.substring(0, 300))
                emit(z.substring(0,300))
            }
            count++
        }
    }




    companion object {

        @JvmStatic
        fun newInstance() = PiFragment()
    }
}