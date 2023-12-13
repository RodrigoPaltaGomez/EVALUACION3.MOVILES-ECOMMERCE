package com.ceduc.comm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CarritoActivity : AppCompatActivity() {
    private lateinit var carritoAdapter: CarritoAdapter
    private val productosCarrito = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val db = SQLiteDB(this)
        productosCarrito.clear()
        productosCarrito.addAll(db.obtenerProductosCarrito())
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCarrito)
        carritoAdapter = CarritoAdapter(this, productosCarrito) { position ->
            carritoAdapter.eliminarProducto(position)
            actualizarVistaSumaTotal()
            carritoAdapter.notifyDataSetChanged()
        }
        recyclerView.adapter = carritoAdapter
        actualizarVistaSumaTotal()
    }
    private fun actualizarVistaSumaTotal() {
        val sumaTotal = calcularSumaTotal()
        val sumaTotalTextView: TextView = findViewById(R.id.sumaTotalTextView)
        sumaTotalTextView.text = "Suma total: $$sumaTotal"
        if (productosCarrito.isEmpty()) {
            finish()
        }
    }
    private fun calcularSumaTotal(): Double {
        var suma = 0.0
        for (producto in productosCarrito) {
            suma += producto.valor * producto.cantidad
        }
        return suma
    }

}
