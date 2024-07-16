package com.francoisbari.facturefacile.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.francoisbari.facturefacile.R
import com.francoisbari.facturefacile.presentation.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}