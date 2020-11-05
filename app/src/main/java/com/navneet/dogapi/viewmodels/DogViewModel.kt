package com.navneet.dogapi.viewmodels

import androidx.lifecycle.*
import com.navneet.dogapi.data.DogRepository
import com.navneet.dogapi.network.BreedJson
import com.navneet.dogapi.ui.BreedImageActiviy
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DogViewModel(private val repository:DogRepository):ViewModel() {
    val dogLists= repository.dogList
    private val _navigateToDetails = MutableLiveData<BreedJson> ()
     val navigateToDetails :LiveData<BreedJson> get() = _navigateToDetails

    fun onDogClick(breed: BreedJson)
    {
       _navigateToDetails.value=breed

    }
    init {
        viewModelScope.launch {
            repository.getBreed()
        }

    }

}


class DogViewModelFactory (private val repository: DogRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DogViewModel::class.java))
        {
            return DogViewModel(repository ) as T
        }

        throw IllegalArgumentException("Invalid ViewModel Class")
    }

}

