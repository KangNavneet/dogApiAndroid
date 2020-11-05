package com.navneet.dogapi

import com.navneet.dogapi.network.DogList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator {


    //MOSHI RETROFIT BUILDER
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://dog.ceo")
        .build()

    val dogList: DogList = retrofit.create(DogList::class.java)

}