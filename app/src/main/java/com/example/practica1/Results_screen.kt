package com.example.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica1.databinding.ActivityMainBinding
import com.example.practica1.databinding.ActivityResultsScreenBinding

class Results_screen : AppCompatActivity() {

    private lateinit var binding: ActivityResultsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Cachar los datos del MainActivity
        val bundle = intent.extras
        val item_position = bundle?.getInt("item_position",0)
        val operation = bundle?.getString("operacion","")
        val varA = bundle?.getString("varA","")
        val var1 = bundle?.getInt("var1",0)
        val varB = bundle?.getString("varB","")
        val var2 = bundle?.getInt("var2",0)
        val result = bundle?.getFloat("resultado",0.0F)

        binding.tvFormula.text = operation
        binding.tvVarAResultado.text = varA
        binding.tvVarBResultado.text = varB
        binding.tvResultado.text = result.toString()


    }
}