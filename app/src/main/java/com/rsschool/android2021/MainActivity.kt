package com.rsschool.android2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.android2021.FirstFragment.Companion.newInstance
import com.rsschool.android2021.SecondFragment.Companion.newInstance

class MainActivity : AppCompatActivity(R.layout.activity_main), ActivityFromMain{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFirstFragment(0)
    }

    override fun openFirstFragment(previousNumber: Int) {

        val firstFragment: Fragment = newInstance(previousNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment).addToBackStack("tag")
        transaction.commit()
    }

    override fun openSecondFragment(min: Int, max: Int) {
        val secondFragment: Fragment = newInstance(min, max)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment).addToBackStack("tag")
        transaction.commit()
    }

}