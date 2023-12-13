package com.ceduc.comm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var db: SQLiteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQLiteDB(this)

        val button1: ImageButton = findViewById(R.id.button1)
        val button2: ImageButton = findViewById(R.id.button2)
        val button3: ImageButton = findViewById(R.id.button3)
        val button4: ImageButton = findViewById(R.id.button4)
        val verCarritoButton: Button = findViewById(R.id.button5)
        val listarProductosButton: Button = findViewById(R.id.button6)

        button1.tag = Producto(1, "Drone", 199.99, 0)
        button2.tag = Producto(2, "Macbook", 999.99, 0)
        button3.tag = Producto(3, "Audífonos", 49.99, 0)
        button4.tag = Producto(4, "Realidad Virtual", 149.99, 0)

        button1.setOnClickListener {
            val producto1 = Producto(1, "Drone", 199.99, 1)
            db.agregarProductoAlCarrito(producto1)
            Toast.makeText(this, "Drone agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            val producto2 = Producto(2, "Macbook", 999.99, 1)
            db.agregarProductoAlCarrito(producto2)
            Toast.makeText(this, "MacBook agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        button3.setOnClickListener {
            val producto3 = Producto(3, "Audífonos", 49.99, 1)
            db.agregarProductoAlCarrito(producto3)
            Toast.makeText(this, "Audífonos agregados al carrito", Toast.LENGTH_SHORT).show()
        }

        button4.setOnClickListener {
            val producto4 = Producto(4, "Realidad Virtual", 149.99, 1)
            db.agregarProductoAlCarrito(producto4)
            Toast.makeText(this, "Realidad Virtual agregada al carrito", Toast.LENGTH_SHORT).show()
        }

        verCarritoButton.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        listarProductosButton.setOnClickListener {
            startActivity(Intent(this, ListadoProductosActivity::class.java))
        }
    }
}
