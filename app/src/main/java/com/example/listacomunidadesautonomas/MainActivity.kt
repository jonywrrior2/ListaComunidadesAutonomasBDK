package com.example.listacomunidadesautonomas

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvComunidad.layoutManager=LinearLayoutManager(this)
        binding.rvComunidad.adapter=Adapter(listaComunidades){ comunidad ->
            onItemSelected(comunidad)
        }
        //esto deja el tamaño del viewHolder fijo y la segunda linea le añade animacion
        binding.rvComunidad.setHasFixedSize(true)
        binding.rvComunidad.itemAnimator=DefaultItemAnimator()
        /* val decoration = DividerItemDecoration(this,RecyclerView.VERTICAL)
        binding.rvFrutas.addItemDecoration(decoration) */ //Esto es para separar con una linea en caso de no poner un cardView

    }
    private fun onItemSelected(comunidad:Comunidad){
        Toast.makeText(this,"Has pulsado sobre ${comunidad.nombre}",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

}