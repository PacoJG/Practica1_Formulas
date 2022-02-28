package com.example.practica1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica1.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var item_position: Int=0
    private var resultado: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivFormula.setImageResource(R.drawable.esperar_formula)
        binding.etVarA.visibility = View.INVISIBLE
        binding.etVarB.visibility = View.INVISIBLE
        binding.btnCalcular.visibility = View.INVISIBLE
        binding.tvDescription.text = resources.getString(R.string.description_initial)

        val lista = resources.getStringArray(R.array.opciones_spinner)
        val adaptador = ArrayAdapter(this,R.layout.items_lista,lista)

        //Expose Drop-Down Menu para reemplazar el spinner
        //Fuente: https://www.youtube.com/watch?v=isZ1vrOuG2o
        with(binding.tilSpinnerOptions){
            setAdapter(adaptador)
            onItemClickListener = this@MainActivity
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item_seleccionado = parent?.getItemAtPosition(position).toString()
        Toast.makeText(this@MainActivity,item_seleccionado,Toast.LENGTH_LONG).show()
        val item_position = position
        when(item_position){
            0 -> {
                binding.etVarA.visibility = View.VISIBLE
                binding.etVarB.visibility = View.VISIBLE
                binding.btnCalcular.visibility = View.VISIBLE
                binding.ivFormula.setImageResource(R.drawable.formula1)
                binding.etVarA.hint = resources.getString(R.string.formula1_var1)
                binding.etVarB.hint = resources.getString(R.string.formula1_var2)
                binding.tvDescription.text = resources.getString(R.string.description_cilindro)
            }
            1 ->{
                binding.etVarA.visibility = View.VISIBLE
                binding.etVarB.visibility = View.VISIBLE
                binding.btnCalcular.visibility = View.VISIBLE
                binding.ivFormula.setImageResource(R.drawable.formula2)
                binding.etVarA.hint = resources.getString(R.string.formula2_var1)
                binding.etVarB.hint = resources.getString(R.string.formula2_var2)
                binding.tvDescription.text = resources.getString(R.string.description_cono)
            }
            2 ->{
                binding.etVarA.visibility = View.VISIBLE
                binding.etVarB.visibility = View.VISIBLE
                binding.btnCalcular.visibility = View.VISIBLE
                binding.ivFormula.setImageResource(R.drawable.formula3)
                //binding.etVarA.setText(resources.getString(R.string.formula3_var1))
                binding.etVarA.hint = resources.getString(R.string.formula3_var1)
                binding.etVarB.hint = resources.getString(R.string.formula3_var2)
                binding.tvDescription.text = resources.getString(R.string.description_prisma)
            }
        }
    }

    private fun validaCampos(): Boolean{
        if( binding.etVarA.text.toString() == "" || binding.etVarB.text.toString() == "" ) return false
        if( binding.etVarA.text.toString() == "" && binding.etVarB.text.toString() != "" ) return false
        if( binding.etVarA.text.toString() != "" && binding.etVarB.text.toString() == "" ) return false
        else return true
    }

    fun click(view: View) {
        if (validaCampos()){
            val var1 = Integer.parseInt(binding.etVarA.text.toString())
            val var2 = Integer.parseInt(binding.etVarB.text.toString())

            when(item_position){
                0 ->{
                    resultado = ((3.141592F*(var1*var1).toFloat())*var2.toFloat())
                }
                1 ->{
                    resultado = ((3.141592F*var1.toFloat())*var2.toFloat())
                }
                2 ->{
                    resultado = (var1.toFloat()*var2.toFloat())
                }
            }

            val intent = Intent(this, Results_screen::class.java)
            val parametros = Bundle()
            parametros.putString("item_position", item_position.toString())
            parametros.putString("operacion",binding.tvDescription.text.toString())
            parametros.putString("varA", binding.etVarA.hint.toString())
            parametros.putInt("var1", var1)
            parametros.putString("varB", binding.etVarB.hint.toString())
            parametros.putInt("var2", var2)
            parametros.putFloat("resultado", resultado)
            intent.putExtras(parametros)
            startActivity(intent)
        }
        else{
            Toast.makeText(this, getString(R.string.error_valores),Toast.LENGTH_LONG).show()
        }

    }
}