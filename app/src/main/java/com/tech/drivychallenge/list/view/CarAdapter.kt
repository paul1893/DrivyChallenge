package com.tech.drivychallenge.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.tech.drivychallenge.R
import com.tech.drivychallenge.list.model.CarViewModel
import kotlinx.android.synthetic.main.item_car.view.*

class CarAdapter(
    private val context: Context,
    private val listener: Listener?
) : RecyclerView.Adapter<CarAdapter.CarViewModelHolder>() {

    interface Listener {
        fun onRowClicked(model: CarViewModel)
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

        fun bind(carViewModel: CarViewModel) = with(carViewModel) {
            view.carnameTextView.text = name
            view.carImageView.setImageResource(R.mipmap.ic_launcher) // TODO PBA
            // TODO PBA load image
            view.priceTextView.text = price
            view.ratingbar.rating = rating
            view.ratingTextView.text = ratingLabel
            view.setOnClickListener { listener?.onRowClicked(carViewModel) }
            ViewCompat.setTransitionName(view.carImageView, carViewModel.id)
        }
    }
}