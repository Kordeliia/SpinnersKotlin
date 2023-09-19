package com.example.spinners


import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.spinners.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private val cities = arrayListOf("Málaga", "Los Ángeles", "Córdoba", "León", "La Paz")
    private val levels = arrayOf("Básico", "Medio", "Difícil")
    private val hobbies = arrayOf("Baile", "Viajes", "Deportes", "Libros", "TV", "Comida", "Moda", "Otros")
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)
        binding.spCities.adapter = cityAdapter
        binding.spCities.onItemSelectedListener = this
        binding.btnAdd.setOnClickListener {
            cities.add(binding.etCity.text.toString())
            binding.etCity.setText("")
        }
            //cards
            val typeAdapter = ArrayAdapter.createFromResource(this, com.example.spinners.R.array.types, R.layout.simple_spinner_dropdown_item)
            binding.spCardType.adapter = typeAdapter

        //levels
        val levelAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        binding.spLevel.adapter = levelAdapter
            binding.spLevel.onItemSelectedListener =  object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Snackbar.make(binding.root, levels[p2], Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Snackbar.make(binding.root, "sin seleccion", Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.btnAdd.setOnLongClickListener{
            levelAdapter.clear()
            levels.forEach{
                levelAdapter.add(it)
            }
            true
        }
        //hobbies
        val hobbyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hobbies)
        (binding.tilHobbies.editText as? AutoCompleteTextView)?.setAdapter(hobbyAdapter)
    }

override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
    val city = cities[position]
    Snackbar.make(binding.root, city, Snackbar.LENGTH_SHORT).show()
}
override fun onNothingSelected(p0: AdapterView<*>?){
    Snackbar.make(binding.root, "sin seleccion", Snackbar.LENGTH_SHORT).show()
}
}