package com.example.listacomunidadesautonomas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listacomunidadesautonomas.ListaComunidades.Companion.listaComunidades
import com.example.listacomunidadesautonomas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  datosCopia:List <Comunidad>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvComunidad.layoutManager=LinearLayoutManager(this)
        binding.rvComunidad.adapter=Adapter(listaComunidades){ comunidad ->
            onItemSelected(comunidad)
        }

        binding.rvComunidad.setHasFixedSize(true)
        binding.rvComunidad.itemAnimator=DefaultItemAnimator()

    datosCopia = ListaComunidades.listaComunidades.toList()
    }
    private fun onItemSelected(comunidad:Comunidad){
        Toast.makeText(this,"Has pulsado sobre ${comunidad.nombre}",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.opcionLimpiar ->{
                ListaComunidades.listaComunidades.clear()
                binding.rvComunidad.adapter?.notifyDataSetChanged()
                true
            }
            R.id.opcionRecargar -> {
                ListaComunidades.listaComunidades.addAll(datosCopia)
                binding.rvComunidad.adapter?.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    }
