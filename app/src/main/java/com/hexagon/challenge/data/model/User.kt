package com.hexagon.challenge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "birth_date")
    val birthDate: String,
    @ColumnInfo(name = "cpf")
    val cpf: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "active")
    val active: Boolean
)