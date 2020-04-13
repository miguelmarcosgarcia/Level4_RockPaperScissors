package com.example.rockpaperscissor.database

import android.content.Context
import com.example.rockpaperscissor.model.Game

class GameRepository (context: Context) {

    private val gameDAO: GameDAO

    init {
        val database =
            GameHistoryRoomDatabase.getDatabase(context)
        gameDAO = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> = gameDAO.getAllGames()

    suspend fun insertGame(game: Game) = gameDAO.insertGame(game)

    suspend fun deleteGame(game: Game) = gameDAO.deleteGame(game)

    suspend fun deleteAllGames() = gameDAO.deleteAllGames()
}
