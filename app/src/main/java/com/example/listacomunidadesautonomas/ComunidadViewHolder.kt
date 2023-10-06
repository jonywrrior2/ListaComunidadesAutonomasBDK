package com.example.listacomunidadesautonomas

import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.listacomunidadesautonomas.databinding.ItemComunidadBinding


class ComunidadViewHolder(view: View):ViewHolder(view) {

    val binding = ItemComunidadBinding.bind(view)

    fun render (item:Comunidad,onClickListener: (Comunidad)->Unit){
        binding.tvComunidadNombre.text=item.nombre
        binding.ivComunidad.setImageResource(item.imagen)
        itemView.setOnClickListener{
            Toast.makeText(
                binding.ivComunidad.context,
                "Has pulsado sobre ${item.nombre}",
                Toast.LENGTH_SHORT
            ).show()
        }

        }
}