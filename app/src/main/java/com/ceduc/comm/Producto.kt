package com.ceduc.comm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Producto(
    val id: Int,
    val nombre: String,
    val valor: Double,
    var cantidad: Int
) : Parcelable
