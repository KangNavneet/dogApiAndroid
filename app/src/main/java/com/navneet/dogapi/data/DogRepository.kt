package com.navneet.dogapi.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.navneet.dogapi.ServiceLocator
import com.navneet.dogapi.network.BreedJson
import com.navneet.dogapi.network.DogImageModel

class DogRepository(private val serviceLocator: ServiceLocator) {

    private val _dogLists = MutableLiveData<List<BreedJson>>()
    val dogList: LiveData<List<BreedJson>> get() = _dogLists


    private val _dogDetails = MutableLiveData<DogImageModel>()
    val dogDetails: LiveData<DogImageModel> get() = _dogDetails


    suspend fun getBreed() {

        try {
            _dogLists.value = serviceLocator.dogList.getBreeds().buildBreedJson()
        } catch (e: Exception) {
        }


    }


    //HTTP REQUEST
    suspend fun getDogDetails(dog: BreedJson) {

        try {
            Log.d("NavneetData", "getDogDetails: HTTP REQUEST!")
            if(dog.subBreed?.isNotEmpty() == true) {
                _dogDetails.value = serviceLocator.dogList.getBreedImage(dog.breed, dog.subBreed)
            }else {
                _dogDetails.value = serviceLocator.dogList.getBreedImage(dog.breed)
            }
        } catch (e: java.lang.Exception) {
            Log.e("err", e.localizedMessage)
        }
    }


}