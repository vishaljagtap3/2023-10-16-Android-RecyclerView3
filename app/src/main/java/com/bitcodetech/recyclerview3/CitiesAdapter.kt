package com.bitcodetech.recyclerview3

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CitiesAdapter(
    private val cities : ArrayList<String>
) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>(){

    interface OnCityClickListener {
        fun onCityClick(position : Int, txtView : TextView)
    }

    var onCityClickListener : OnCityClickListener? = null

    inner class CityViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var txtCity : TextView

        init {
            txtCity = view as TextView

            txtCity.setOnClickListener {
                //hard coding an action in a listener inside an adapter is not good
                //action 1
                //Toast.makeText(it.context, "${cities[adapterPosition]} clicked...", Toast.LENGTH_SHORT).show()
                if(onCityClickListener != null) {
                    onCityClickListener!!.onCityClick(
                        adapterPosition,
                        txtCity
                    )
                }
            }
        }
    }

    override fun getItemCount() = cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val txtCity = TextView(parent.context)
        txtCity.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        txtCity.textSize = 30f
        txtCity.setPadding(20, 20, 20, 20)

        return CityViewHolder(txtCity)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.txtCity.text = cities[position]
    }
}