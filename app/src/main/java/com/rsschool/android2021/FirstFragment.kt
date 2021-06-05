package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.lang.Math.pow
import java.lang.NumberFormatException
import java.math.BigInteger
import kotlin.math.max

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    private var listener: ActivityFromMain? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = context as ActivityFromMain

        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val editTextMin : EditText = view.findViewById(R.id.min_value)
        val editTextMax : EditText = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            val min = editTextMin.text.toString()
            val max = editTextMax.text.toString()

            if (min != "" && max != "") {
                try {
                    val min_v = min.toInt()
                    val max_v = max.toInt()
                    if (min_v <= max_v)
                        listener?.openSecondFragment(min_v, max_v)
                    else
                        Toast.makeText(context, "Min должно быть меньше Max", Toast.LENGTH_SHORT).show()

                }catch (e: NumberFormatException){
                    Toast.makeText(context, "Введены слишком большие значения, необходимо задать меньшие", Toast.LENGTH_SHORT).show()
                    editTextMin.text.clear()
                    editTextMax.text.clear()
                }
            }
            else{
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
                            }
    }

    companion object {

        fun newInstance(previousResult: Int): FirstFragment {
            return FirstFragment().apply {
                arguments = bundleOf(
                    PREVIOUS_RESULT_KEY to previousResult
                )
            }
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}