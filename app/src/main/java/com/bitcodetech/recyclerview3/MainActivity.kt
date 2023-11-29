package com.bitcodetech.recyclerview3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerCities: RecyclerView
    private val cities = ArrayList<String>()
    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var btnAdd: Button
    private lateinit var btnDelete: Button
    private lateinit var edtCity: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        recyclerCities = findViewById(R.id.recyclerCities);
        btnAdd = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btnDelete)
        edtCity = findViewById(R.id.edtCity)

        citiesAdapter = CitiesAdapter(cities)
        citiesAdapter.onCityClickListener = MyCityClickListener()
        recyclerCities.adapter = citiesAdapter
        recyclerCities.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        btnAdd.setOnClickListener {
            cities.add(edtCity.text.toString())
            //citiesAdapter.notifyDataSetChanged()
            citiesAdapter.notifyItemInserted(cities.size - 1)
        }

        btnDelete.setOnClickListener {
            val cityIndex = cities.indexOf(edtCity.text.toString())
            if (cityIndex != -1) {
                cities.remove(edtCity.text.toString())
                citiesAdapter.notifyItemRemoved(cityIndex)
            } else {
                Toast.makeText(this, "City not found!", Toast.LENGTH_SHORT).show()
            }

            //citiesAdapter.notifyDataSetChanged()
        }


    }

    inner class MyCityClickListener : CitiesAdapter.OnCityClickListener {
        override fun onCityClick(position: Int, txtView: TextView) {
            Toast.makeText(
                this@MainActivity,
                "MyAction: ${cities[position]} is clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initData() {
        cities.add("Pune");
        cities.add("Mumbai");
        cities.add("Nagpur");
        cities.add("Kolhapur");
        cities.add("Nahik");
    }
}

