package me.snowshadow.meauw.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.snowshadow.meauw.repo.Cat

@TypeConverters(CatConverter::class)
@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun catDao(): CatDao
}