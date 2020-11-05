package com.navneet.dogapi.viewmodels

import androidx.lifecycle.*
import com.navneet.dogapi.data.DogRepository
import com.navneet.dogapi.network.BreedJson
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DetailViewModel(private val repository: DogRepository, private val dog: BreedJson) : ViewModel() {
    val dogDetails = repository.dogDetails

    private val _shareImageData = MutableLiveData<String>()
    val shareImage: LiveData<String> get() = _shareImageData
    private val _browseImageData= MutableLiveData<String>()
    val browseImage: LiveData<String> get() = _browseImageData


    init {
        viewModelScope.launch {
            repository.getDogDetails(dog)
        }

    }



    fun browseImage() {
        _browseImageData.value = dogDetails.value?.message
    }


    fun shareImage() {
        _shareImageData.value = dogDetails.value?.message
    }



}


class DetailsViewModelFactory(private val repository: DogRepository, private val breed: BreedJson) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository, breed) as T
        }

        throw IllegalArgumentException("Not Supported!")
    }


}