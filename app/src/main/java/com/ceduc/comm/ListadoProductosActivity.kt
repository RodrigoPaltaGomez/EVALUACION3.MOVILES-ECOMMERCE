package com.ceduc.comm

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListadoProductosActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_productos)
        listView = findViewById(R.id.listViewProductos)
        val db = SQLiteDB(this)
        val productosCarrito = db.obtenerProductosCarrito()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productosCarrito.map { it.nombre })
        listView.adapter = adapter
    }
}
