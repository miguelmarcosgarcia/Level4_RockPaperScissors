package com.example.rockpaperscissor

import androidx.room.TypeConverter
import com.example.rockpaperscissor.model.Action
import java.util.*

class Converter {
    @TypeConverter
    fun intToAction(value: Int): Action {
        return when (value) {
            0 -> Action.Rock
            1 -> Action.Paper
            2 -> Action.Scissors
            else -> Action.Scissors
        }
    }

    @TypeConverter
    fun actionToInt(action: Action): Int? {
        return action.value
    }

    @TypeConverter
    fun dateToTime(date: Date?): Long? {
        return date?.time
    }


    @TypeConverter
    fun fromTime(value: Long?): Date? {
        return value?.let { Date(it) }
    }
}