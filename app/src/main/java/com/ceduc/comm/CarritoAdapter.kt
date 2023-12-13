package com.ceduc.comm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter(
    private val context: Context,
    private val productos: MutableList<Producto>,
    private val onEliminarProducto: (Int) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    fun eliminarProducto(position: Int) {
        if (position >= 0 && position < productos.size) {
            val productoEliminado = productos.removeAt(position)
            SQLiteDB(context).eliminarProductoDelCarrito(productoEliminado.id)
            notifyItemRemoved(position)
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreProductoTextView: TextView = itemView.findViewById(R.id.nombreProductoTextView)
        val cantidadProductoTextView: TextView = itemView.findViewById(R.id.cantidadProductoTextView)
        val eliminarProductoButton: Button = itemView.findViewById(R.id.eliminarProductoButton)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto_carrito, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]

        holder.nombreProductoTextView.text = producto.nombre
        holder.cantidadProductoTextView.text = "Cantidad: ${producto.cantidad}"

        holder.eliminarProductoButton.setOnClickListener {
            onEliminarProducto(position)
        }
    }
    override fun getItemCount(): Int {
        return productos.size
    }
}
