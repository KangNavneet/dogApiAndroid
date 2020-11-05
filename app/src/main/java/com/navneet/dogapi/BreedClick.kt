package com.navneet.dogapi

import com.navneet.dogapi.network.BreedJson

class BreedClick(private val breedClick: (breedJson: BreedJson) -> Unit) {


    fun breedClickListener(data: BreedJson) {

        breedClick(data)


    }
}