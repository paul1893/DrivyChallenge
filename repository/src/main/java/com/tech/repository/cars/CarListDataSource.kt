package com.tech.repository.cars

import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.GET
import java.util.Collections.emptyList

class CarListDataSource(
    private val service: CarListService
) {
    fun getCarList(): List<CarJSON> = service.getCarList().execute().body() ?: emptyList() // We can also throw an exception to propagate the error
}

interface CarListService {
    @GET("cars.json")
    fun getCarList(): Call<List<CarJSON>>
}

@JsonClass(generateAdapter = true)
data class CarJSON(
    val model: String,
    val brand: String,
    val picture_url: String,
    val price_per_day: Int,
    val rating: RatingJSON,
    val owner: OwnerJSON
)

@JsonClass(generateAdapter = true)
data class RatingJSON(
    val average: Float,
    val count: Int
)

@JsonClass(generateAdapter = true)
data class OwnerJSON(
    val name: String,
    val picture_url: String,
    val rating: RatingJSON
)