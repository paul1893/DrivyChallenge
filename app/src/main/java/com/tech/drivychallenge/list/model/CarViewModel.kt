package com.tech.drivychallenge.list.model

data class CarViewModel(
    val id: String,
    val imageURL: String,
    val name: String,
    val price: String,
    val rating: Float,
    val ratingLabel: String
)