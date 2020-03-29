package com.egecius.architecturesdemo.cleanarch.b_adapters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.egecius.architecturesdemo.cleanarch.d_domain.Car

/** Database Access Object for cars */
@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    suspend fun loadAllCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Query("DELETE FROM Cars")
    fun deleteAllCars()

}
