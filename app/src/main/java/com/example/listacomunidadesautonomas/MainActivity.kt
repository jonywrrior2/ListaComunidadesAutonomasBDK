package com.example.listacomunidadesautonomas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listacomunidadesautonomas.ListaComunidades.Companion.listaComunidades
import com.example.listacomunidadesautonomas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){

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
            R.id.opcionAñadir ->{
                addComunidad()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun addComunidad(){
        listaComunidades.add(listaComunidades.size, Comunidad("Nueva Comunidad ${listaComunidades.size}",
            R.drawable.andalucia))
            binding.rvComunidad.adapter?.notifyItemInserted (listaComunidades.size)
            binding.rvComunidad.layoutManager?.scrollToPosition(listaComunidades.size)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        lateinit var comunidadAfectada: Comunidad
        lateinit var miIntent: Intent
        comunidadAfectada = listaComunidades[item.groupId]
        when(item.itemId){
            0 ->{
                val alert =
                    AlertDialog.Builder(this).setTitle("Eliminar ${comunidadAfectada.nombre}")
                        .setMessage("Estas seguro de que quieres eliminar ${comunidadAfectada.nombre}?")
                        .setNeutralButton("Cerrar", null).setPositiveButton(
                            "Aceptar"
                        ){_,_ ->
                            display("Se ha eliminado ${comunidadAfectada.nombre}")
                            listaComunidades.removeAt(item.groupId)
                            binding.rvComunidad.adapter?.notifyItemRemoved(item.groupId)
                            binding.rvComunidad.adapter?.notifyItemRangeChanged(item.groupId, listaComunidades.size)
                            binding.rvComunidad.adapter = Adapter(listaComunidades){comunidad ->
                                onItemSelected(comunidad)

                            }
                        }.create()
                alert.show()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    private fun display (message: String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    }
