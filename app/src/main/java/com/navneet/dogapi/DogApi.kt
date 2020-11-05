package com.navneet.dogapi

import android.app.Application

class DogApi: Application() {
    val serviceLocator = ServiceLocator()
}