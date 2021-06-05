package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.random.Random.Default.nextInt

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null

    private var listener: ActivityFromMain? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        val answer = generate(min, max)
        result?.text = answer.toString()

        listener = context as ActivityFromMain

        backButton?.setOnClickListener {
            listener?.openFirstFragment(answer)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        if(min == max) return min
        return  (min until (max + 1)).random()
    }

    companion object {

        fun newInstance(min: Int, max: Int): SecondFragment {
            return SecondFragment().apply {
                arguments = bundleOf(
                    MIN_VALUE_KEY to min,
                    MAX_VALUE_KEY to max
                )
            }
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}