package com.hexagon.challenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("ArrayInDataClass")
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "birth_date")
    val birthDate: String,
    @ColumnInfo(name = "cpf")
    val cpf: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.BLOB)
    val avatar: ByteArray? = ByteArray(0),
    @ColumnInfo(name = "active", defaultValue = "0")
    val active: Boolean
)