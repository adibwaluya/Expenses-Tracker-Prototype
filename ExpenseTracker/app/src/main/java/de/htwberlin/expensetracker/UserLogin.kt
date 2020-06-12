package de.htwberlin.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_user_login.*

class UserLogin: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_user_login)

        login_button.setOnClickListener {
            startActivity(Intent(this, MainPage::class.java))
        }
    }
}