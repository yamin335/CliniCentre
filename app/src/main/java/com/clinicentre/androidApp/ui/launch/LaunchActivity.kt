package com.clinicentre.androidApp.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clinicentre.androidApp.R
import com.clinicentre.androidApp.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            launch {
                delay(500L)
            }

            startActivity(
                Intent(this@LaunchActivity, MainActivity::class.java)
            )
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left)
            finish()
        }
    }
}