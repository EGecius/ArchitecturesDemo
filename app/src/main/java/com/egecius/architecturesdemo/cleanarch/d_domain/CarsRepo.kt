package com.egecius.architecturesdemo.cleanarch.d_domain

interface CarsRepo {

    suspend fun getCars(): List<Car>
}