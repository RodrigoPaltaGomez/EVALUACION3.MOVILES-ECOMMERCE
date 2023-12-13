package com.ceduc.comm

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "EcommerceDB"
        private const val TABLE_CARRITO = "carrito"
        private const val KEY_ID = "id"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_VALOR = "valor"
        private const val KEY_CANTIDAD = "cantidad"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableCarrito = ("CREATE TABLE $TABLE_CARRITO($KEY_ID INTEGER PRIMARY KEY, $KEY_NOMBRE TEXT, $KEY_VALOR REAL, $KEY_CANTIDAD INTEGER)")
        db.execSQL(createTableCarrito)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CARRITO")
        onCreate(db)
    }
    fun agregarProductoAlCarrito(producto: Producto) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NOMBRE, producto.nombre)
        values.put(KEY_VALOR, producto.valor)
        values.put(KEY_CANTIDAD, producto.cantidad)
        db.insert(TABLE_CARRITO, null, values)
        db.close()
    }
    @SuppressLint("Range")
    fun obtenerProductosCarrito(): List<Producto> {
        val productList = mutableListOf<Producto>()
        val selectQuery = "SELECT * FROM $TABLE_CARRITO"
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    val nombre = cursor.getString(cursor.getColumnIndex(KEY_NOMBRE))
                    val valor = cursor.getDouble(cursor.getColumnIndex(KEY_VALOR))
                    val cantidad = cursor.getInt(cursor.getColumnIndex(KEY_CANTIDAD))
                    val producto = Producto(id, nombre, valor, cantidad)
                    productList.add(producto)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        db.close()
        return productList
    }
    fun eliminarProductoDelCarrito(idProducto: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_CARRITO, "$KEY_ID = ?", arrayOf(idProducto.toString()))
        db.close()
    }
    fun borrarCarrito() {
        val db = this.writableDatabase
        db.delete(TABLE_CARRITO, null, null)
        db.close()
    }
}
