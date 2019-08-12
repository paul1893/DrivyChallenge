package com.tech.drivychallenge.detail.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import com.tech.drivychallenge.DrivyChallengeApplication
import com.tech.drivychallenge.R
import com.tech.drivychallenge.detail.controller.CarDetailController
import com.tech.drivychallenge.detail.model.CarDetailViewModel
import com.tech.drivychallenge.detail.module.CarDetailModule
import com.tech.drivychallenge.detail.presenter.CarDetailObservable
import kotlinx.android.synthetic.main.activity_card_detail.*
import javax.inject.Inject

class CarDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var controller: CarDetailController
    @Inject
    lateinit var observable: CarDetailObservable
    @Inject
    lateinit var imageLoader: ImageLoader

    companion object {
        private const val CAR_ID: String = "CAR_ID"
        fun newIntent(context: Context, carId: String) = Intent(context, CarDetailActivity::class.java)
            .putExtra(CAR_ID, carId)
    }

    private val carId by lazy { intent.getStringExtra(CAR_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        DrivyChallengeApplication.getComponent(applicationContext)
            .plus(CarDetailModule())
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        initToolbar()
        initObservers()
        controller.loadCar(carId)
    }

    fun initToolbar() = supportActionBar?.let {
        it.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObservers() = with(observable) {
        car.observe(this@CarDetailActivity, Observer(::onReceivedCar))
    }

    private fun onReceivedCar(carModel: CarDetailViewModel) {
        with(carModel) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                carImageView.transitionName = carModel.id
            }

            carnameTextView.text = name
            priceTextView.text = price
            ratingbar.rating = rating
            ratingTextView.text = ratingLabel
            personNameTextView.text = personName
            personRatingbar.rating = personRating

            imageLoader.displayImage(personImageUrl, personImageView)
            imageLoader.displayImage(imageURL, carImageView)
        }
    }
}