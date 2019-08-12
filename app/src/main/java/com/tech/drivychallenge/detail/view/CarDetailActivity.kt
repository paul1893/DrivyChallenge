package com.tech.drivychallenge.detail.view

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
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
import kotlin.math.max

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
        initTransitions()
        initToolbar()
        initObservers()
        controller.loadCar(carId)
    }

    override fun onBackPressed() {
        val animator = ViewAnimationUtils.createCircularReveal(
            personImageView,
            (personImageView.left + personImageView.right) / 2,
            (personImageView.top + personImageView.bottom) / 2,
            max(personImageView.width, personImageView.height).toFloat(),
            0f
        )
        animator.start()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                /* do nothing */
            }

            override fun onAnimationEnd(animation: Animator?) {
                personImageView.visibility = View.INVISIBLE
                supportFinishAfterTransition()
            }

            override fun onAnimationCancel(animation: Animator?) {
                /* do nothing */
            }

            override fun onAnimationStart(animation: Animator?) {
                /* do nothing */
            }

        })
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

    private fun initToolbar() = supportActionBar?.let {
        it.setDisplayHomeAsUpEnabled(true)
    }

    private fun initTransitions() {
        supportPostponeEnterTransition()
        window.enterTransition = Slide(Gravity.BOTTOM).apply {
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        window.sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                val animator = ViewAnimationUtils.createCircularReveal(
                    personImageView,
                    (personImageView.left + personImageView.right) / 2,
                    (personImageView.top + personImageView.bottom) / 2,
                    0f,
                    max(personImageView.width, personImageView.height).toFloat()
                )
                personImageView.visibility = View.VISIBLE
                animator.start()
                window.sharedElementEnterTransition.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition?) {
                /* do nothing */
            }

            override fun onTransitionPause(transition: Transition?) {
                /* do nothing */
            }

            override fun onTransitionCancel(transition: Transition?) {
                /* do nothing */
            }

            override fun onTransitionStart(transition: Transition?) {
                personImageView.visibility = View.INVISIBLE
            }
        })
    }

    private fun initObservers() = with(observable) {
        car.observe(this@CarDetailActivity, Observer(::onReceivedCar))
        error.observe(this@CarDetailActivity, Observer{ finish() })
    }

    private fun onReceivedCar(carModel: CarDetailViewModel) {
        with(carModel) {
            carImageView.transitionName = carModel.id
            carnameTextView.text = name
            priceTextView.text = price
            ratingbar.rating = rating
            ratingTextView.text = ratingLabel
            personNameTextView.text = personName
            personRatingbar.rating = personRating

            imageLoader.displayImage(personImageUrl, personImageView)
            imageLoader.displayImage(imageURL, carImageView, object : ImageLoadingListener {
                override fun onLoadingStarted(imageUri: String?, view: View?) {
                    /* do nothing */
                }

                override fun onLoadingCancelled(imageUri: String?, view: View?) {
                    supportStartPostponedEnterTransition()
                }

                override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
                    supportStartPostponedEnterTransition()
                }

                override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {
                    supportStartPostponedEnterTransition()
                }

            })
        }
    }
}