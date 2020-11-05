package com.navneet.dogapi.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.navneet.dogapi.BreedClick
import com.navneet.dogapi.BundleKeys
import com.navneet.dogapi.R
import com.navneet.dogapi.network.BreedJson
import kotlinx.android.extensions.LayoutContainer

class MainAdapter(private val breedClick: BreedClick) : RecyclerView.Adapter<CustomViewHolder>() {
    var breedJson = emptyList<BreedJson>()

    //BUILD VIEWHOLDER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //INFLATE THE VIEW
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_layout, parent, false)
        //RETURN THE CUSTOMEVIEW HOLDER
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.bindItem(breedJson[position], breedClick)

    //GET THE ITEM COUNT
    override fun getItemCount(): Int = breedJson.size
}

//INHERIT THE RECYCLERVIEW VIEWHOLDER CLASS
class CustomViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(breed: BreedJson, breedClick: BreedClick) {
        val textView = itemView.findViewById(R.id.breedEntry) as TextView
        textView.text = breed.title

        containerView.setOnClickListener {


            val intent = Intent(containerView.context, BreedImageActiviy::class.java).apply {
                putExtra(BundleKeys.DOG_DETAIL, breed)

            }
            containerView.context.startActivity(intent)


        }
    }
}