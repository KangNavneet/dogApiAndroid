package com.navneet.dogapi.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.navneet.dogapi.BundleKeys
import com.navneet.dogapi.DogApi
import com.navneet.dogapi.R
import com.navneet.dogapi.data.DogRepository
import com.navneet.dogapi.network.BreedJson
import com.navneet.dogapi.viewmodels.DetailViewModel
import com.navneet.dogapi.viewmodels.DetailsViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_breed_image_activiy.*

class BreedImageActiviy: AppCompatActivity() {
     lateinit var dogImageData: BreedJson
    //lateinit var viewModel: DogImgViewModel
    private val viewModel by viewModels<DetailViewModel>{

        val apiService = (application as DogApi).serviceLocator
        val repository = DogRepository (apiService)
        dogImageData = intent.getSerializableExtra(BundleKeys.DOG_DETAIL) as BreedJson
        DetailsViewModelFactory(repository,dogImageData)
    }


    lateinit var  url: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_image_activiy)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        viewModel.dogDetails.observe(this, Observer {
            Picasso.with(this).load(it.message).into(breedImage)
            url = it.message
            System.out.println("MYDATA")
            System.out.println(it);
            Log.d("logData", "onCreate: "+it.message )
        })

        viewModel.browseImage.observe(this, Observer {
            it?.let {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(it)
                startActivity(intent)
            }
        })

        viewModel.shareImage.observe(this, Observer {
            it?.let {


                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra("shareImageData", "Image Share")
                    putExtra("shareUrlData", it)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share Image Via Chooser"))

            }
        })
    }





//MENU CODE

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if(item.itemId == R.id.shareImg) {

            viewModel.shareImage()

        }
        else if(item.itemId == R.id.browseImg)
        {
            viewModel.browseImage()
        }
        return super.onOptionsItemSelected(item)
    }
}

