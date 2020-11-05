package com.navneet.dogapi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navneet.dogapi.*
import com.navneet.dogapi.data.DogRepository
import com.navneet.dogapi.viewmodels.DogViewModel
import com.navneet.dogapi.viewmodels.DogViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

// BY Navneet Kang
//Student Id :::: A00218574

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MainAdapter
    //private lateinit var viewModel: DogViewModel
    private val viewModel by viewModels<DogViewModel>
    {
        val apiService = (application as DogApi).serviceLocator
        val repository = DogRepository (apiService)
        DogViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addRecyclerView()



        //viewModel =ViewModelProvider(this,viewModelFactory).get(DogViewModel::class.java)


        viewModel.dogLists.observe(this, Observer {

            adapter.breedJson = it
            adapter.notifyDataSetChanged()
        })

        viewModel.navigateToDetails.observe(this, Observer {
            it?.let {
                val intent = Intent(this, BreedImageActiviy::class.java).apply {
                    putExtra(BundleKeys.DOG_DETAIL, it)
                }
                startActivity(intent)
            }
        })




    }

    private fun addRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        adapter =
            MainAdapter(BreedClick { data ->

                Toast.makeText(applicationContext, data.title, Toast.LENGTH_SHORT).show()
                viewModel.onDogClick(data)
            })
        recyclerView.adapter = adapter





/*
        val dogList = (application as DogApi).serviceLocator.dogList
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val data = dogList.getBreeds()
                val dogBreedJsonList = data.buildBreedJson()
                adapter.breedJson = dogBreedJsonList
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {

            }
        }

*/
    }
}