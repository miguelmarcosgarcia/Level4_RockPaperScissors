package com.example.rockpaperscissor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rockpaperscissor.Converter
import com.example.rockpaperscissor.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class GameHistoryRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDAO

    companion object {
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSOR_DB"

        @Volatile
        private var gameHistoryRoomDatabase: GameHistoryRoomDatabase? = null

        fun getDatabase(context: Context): GameHistoryRoomDatabase? {
            if (gameHistoryRoomDatabase == null) {
                synchronized(GameHistoryRoomDatabase::class.java) {
                    if (gameHistoryRoomDatabase == null) {
                        gameHistoryRoomDatabase =
                            Room.databaseBuilder(context.applicationContext,
                                GameHistoryRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return gameHistoryRoomDatabase
        }
    }

}