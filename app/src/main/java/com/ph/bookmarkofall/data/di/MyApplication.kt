package com.ph.bookmarkofall.data.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
       /* var keyHash = Utility.getKeyHash(this)
        Log.d(TAG, "MyApplication - onCreate: $keyHash");*/
    }
}