package com.tech.drivychallenge.detail.model

data class CarDetailViewModel(
    val id: String,
    val imageURL: String,
    val name: String,
    val price: String,
    val rating: Float,
    val ratingLabel: String,
    val personName: String,
    val personImageUrl: String,
    val personRating: Float
)