package de.htwberlin.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_main_page.*

class MainPage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_main_page)

        income_button.setOnClickListener {
            startActivity(Intent(this, IncomePage::class.java))
        }
    }
}