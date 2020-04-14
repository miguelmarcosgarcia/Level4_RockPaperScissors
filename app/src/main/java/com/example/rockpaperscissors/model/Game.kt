package com.example.rockpaperscissor.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "game_table")
data class Game(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "result")
    var result: String,

    @ColumnInfo(name = "time")
    var time: Date,

    @ColumnInfo(name = "computerPick")
    val computerPick: Action,

    @ColumnInfo(name = "playerPick")
    val playerPick: Action

) : Parcelable