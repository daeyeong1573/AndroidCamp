package org.arr.software.androidcamp

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import org.arr.software.androidcamp.utils.MyRealmMigration

private const val TAG = "TodoAppSampleKotlinApplication"

class TodoAppSampleKotlinApplication : Application() {

    @SuppressLint("LongLogTag")
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "called onCreate")
        Realm.init(this)
        realmMigration()
    }
    private fun realmMigration() {

        val realmConfig = RealmConfiguration.Builder()
            .schemaVersion(1L)
            .migration(MyRealmMigration())
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}