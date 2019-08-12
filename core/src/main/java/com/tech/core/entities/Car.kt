package com.tech.core.entities

data class Car(
    val model: String,
    val brand: String,
    val pictureURL: String,
    val pricePerDay: Int,
    val rating: Rating,
    val owner: Owner
)

data class Rating(
    val average: Float,
    val count: Int
)

data class Owner(
    val name: String,
    val pictureURL: String,
    val rating: Rating
)