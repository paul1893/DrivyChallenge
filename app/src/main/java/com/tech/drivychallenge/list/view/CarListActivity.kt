package com.tech.drivychallenge.list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tech.drivychallenge.DrivyChallengeApplication
import com.tech.drivychallenge.R
import com.tech.drivychallenge.detail.view.CarDetailActivity
import com.tech.drivychallenge.list.controller.CarListController
import com.tech.drivychallenge.list.injection.CarListModule
import com.tech.drivychallenge.list.model.CarViewModel
import com.tech.drivychallenge.list.presenter.CarListObservable
import kotlinx.android.synthetic.main.activity_car_list.*
import javax.inject.Inject

class CarListActivity : AppCompatActivity(), CarAdapter.Listener {

    @Inject
    lateinit var controller: CarListController
    @Inject
    lateinit var observable: CarListObservable
    private val adapter: CarAdapter by lazy { CarAdapter(this, this) }

    companion object {
        private const val EXTRA_IMAGE_TRANSITION_NAME = "EXTRA_IMAGE_TRANSITION_NAME"
        private const val NUMBER_OF_COLUMNS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DrivyChallengeApplication.getComponent(applicationContext)
            .plus(CarListModule())
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, NUMBER_OF_COLUMNS)
        initObservers()
        controller.loadCars()
    }

    private fun initObservers() = with(observable) {
        carList.observe(this@CarListActivity, Observer(::onReceivedCars))
    }

    private fun onReceivedCars(carList: List<CarViewModel>) {
        this.adapter.carList = carList
        this.adapter.notifyDataSetChanged()
    }

    override fun onRowClicked(model: CarViewModel, sharedImageView: ImageView) {
        val transitionName = ViewCompat.getTransitionName(sharedImageView)
        val intent = CarDetailActivity.newIntent(this, model.id)
            .putExtra(EXTRA_IMAGE_TRANSITION_NAME, transitionName)
        if (transitionName != null) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                transitionName
            )
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }
}
