package com.egecius.architecturesdemo.cleanarch.a_frameworks.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.egecius.architecturesdemo.cleanarch.b_adapters.network.CarDao
import com.egecius.architecturesdemo.cleanarch.d_domain.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarsDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        @Volatile
        private var INSTANCE: CarsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CarsDatabase {

            val snapshot = INSTANCE

            return if (snapshot != null) {
                snapshot
            } else {
                val newInstance = Room.databaseBuilder(context, CarsDatabase::class.java, "Sample.db").build()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}
