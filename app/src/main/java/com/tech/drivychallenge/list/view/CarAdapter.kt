package com.tech.drivychallenge.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.tech.drivychallenge.DrivyChallengeApplication
import com.tech.drivychallenge.R
import com.tech.drivychallenge.list.model.CarViewModel
import kotlinx.android.synthetic.main.item_car.view.*

class CarAdapter(
    private val context: Context,
    private val listener: Listener?
) : RecyclerView.Adapter<CarAdapter.CarViewModelHolder>() {

    interface Listener {
        fun onRowClicked(model: CarViewModel, sharedImageView: ImageView)
    }

    var carList: List<CarViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarViewModelHolder(
        LayoutInflater.from(context).inflate(R.layout.item_car, parent, false),
        listener
    )

    override fun getItemCount(): Int = carList.size

    override fun onBindViewHolder(holder: CarViewModelHolder, position: Int) {
        holder.bind(carList[position])
    }

    class CarViewModelHolder(
        private val view: View,
        private val listener: Listener?
    ) : RecyclerView.ViewHolder(view) {

        private fun getImageLoader()
                = DrivyChallengeApplication.getComponent(view.context).imageLoader()

        fun bind(carViewModel: CarViewModel) = with(carViewModel) {
            view.carnameTextView.text = name
            val options = DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_empty)
            .build()
            getImageLoader().displayImage(imageURL, view.carImageView, options)
            view.priceTextView.text = price
            view.ratingbar.rating = rating
            view.ratingTextView.text = ratingLabel
            view.setOnClickListener { listener?.onRowClicked(carViewModel, view.carImageView) }
            ViewCompat.setTransitionName(view.carImageView, carViewModel.id)
        }
    }
}