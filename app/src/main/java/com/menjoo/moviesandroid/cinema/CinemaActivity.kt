package com.menjoo.moviesandroid.cinema

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.menjoo.moviesandroid.R

class CinemaActivity : AppCompatActivity() {

    private lateinit var listFragment: CinemaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cinema_activity)
        title = getString(R.string.cinemaTitle)

        if (savedInstanceState == null) {
            listFragment = CinemaFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.cinemaListFragment, listFragment)
                    .commit()
        } else {
            listFragment = supportFragmentManager.findFragmentById(R.id.cinemaListFragment) as CinemaFragment
        }
    }
}
