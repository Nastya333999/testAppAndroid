package eu.funventure.testapp

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import eu.funventure.testapp.data.local.AppDatabase

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}