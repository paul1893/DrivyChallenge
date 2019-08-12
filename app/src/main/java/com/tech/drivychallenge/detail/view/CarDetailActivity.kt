package com.tech.drivychallenge.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tech.drivychallenge.R

class CarDetailActivity : AppCompatActivity() {

    companion object {
        private const val CAR_ID: String = "CAR_ID"
        fun newIntent(context: Context, carId: String) = Intent(context, CarDetailActivity::class.java)
            .putExtra(CAR_ID, carId)
    }

    private val carId by lazy { intent.getStringExtra(CAR_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)
    }
}