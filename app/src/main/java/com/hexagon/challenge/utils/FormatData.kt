package com.hexagon.challenge.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.Serializable

class FormatData {
    companion object {
        fun formatCPF(cpf: String): String {
            return "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${cpf.substring(6, 9)}-" +
                    cpf.substring(9, 11)
        }

        fun formatBirthDate(birthDate: String): String {
            return "${birthDate.substring(0, 2)}/${birthDate.substring(2, 4)}/" +
                    birthDate.substring(4, 8)
        }

        fun Context.getBitmapFromUri(uri: Uri): Bitmap? {
            return try {
                contentResolver.openInputStream(uri)?.use {
                    BitmapFactory.decodeStream(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }

        fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            return bitmap.asImageBitmap()
        }
    }
}