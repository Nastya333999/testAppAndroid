package eu.funventure.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataNumbers(): NumberInfoDao
}