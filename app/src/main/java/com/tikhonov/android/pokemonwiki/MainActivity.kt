package com.tikhonov.android.pokemonwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun toSearchActivity(view : View) {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    fun toRandomActivity(view : View) {
        val intent = Intent(this, RandomActivity::class.java)
        startActivity(intent)
    }
}