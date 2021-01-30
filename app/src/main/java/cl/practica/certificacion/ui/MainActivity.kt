package cl.practica.certificacion.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.practica.certificacion.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}