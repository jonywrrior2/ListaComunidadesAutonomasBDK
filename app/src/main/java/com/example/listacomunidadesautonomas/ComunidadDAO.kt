package com.example.listacomunidadesautonomas

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class ComunidadDAO {
    fun cargarLista(context: Context?): MutableList <Comunidad>{
        lateinit var res:MutableList<Comunidad>
        lateinit var c: Cursor
        try {
            //Obtener acceso de solo lectura
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            // val sql = "SELECT * FROM frutas;"
            // c = db.rawQuery(sql, null)
            val columnas = arrayOf(ComunidadContract.Companion.Entrada.COLUMNA_ID,
            ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE,
            ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN)
            c=db.query(ComunidadContract.Companion.Entrada.NOMBRE_TABLA,
                columnas,null,null,null,null,null)
            res = mutableListOf()
            //Leer los resultados del cursor e insertarlos en la lista
            while (c.moveToNext()){
                val nueva = Comunidad(c.getInt(0),c.getString(1),c.getInt(2))
                res.add(nueva)
            }
        } finally {
            c.close()
        }
        return res
    }

    fun actualizarBBDD(context: Context?,comunidad: Comunidad){
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        val values = ContentValues()
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_ID,comunidad.id)
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE,comunidad.nombre)
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN,comunidad.imagen)
        db.update(ComunidadContract.Companion.Entrada.NOMBRE_TABLA,values,"id=?", arrayOf(comunidad.id.toString()))
        db.close()
    }
}