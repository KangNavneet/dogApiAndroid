package com.navneet.dogapi.network


import retrofit2.http.GET
import retrofit2.http.Path

interface DogList {

    //https://dog.ceo/api/breeds/list/all
    @GET("/api/breeds/list/all")
    suspend fun getBreeds(): Model


    //  https://dog.ceo/api/breed/buhund/images


    @GET("/api/breed/{breed_name}/{sub_breed_name}/images/random")
    suspend fun getBreedImage(
        @Path("breed_name") breedName: String,
        @Path("sub_breed_name") subBreed: String?
    ): DogImageModel


    @GET("/api/breed/{breed_name}/images/random")
    suspend fun getBreedImage(
        @Path("breed_name") breedName: String
    ): DogImageModel



    /*
        @GET("/api/breeds/image/random")
        fun getImage(): Call<model>
     */

}