package com.example.rockpaperscissor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissor.database.GameRepository
import com.example.rockpaperscissor.model.Action
import com.example.rockpaperscissor.model.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {
    // lateinit keyword will let kotlin know that this variable will definitely be initialized at
    // a later stage. We can only initialize it in or after the onCreate because we need to pass
    // the activity context to it.
    private lateinit var gameRepository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        btnScissor.setOnClickListener { gameSimulator(Action.Scissors) }
        btnPaper.setOnClickListener { gameSimulator(Action.Paper) }
        btnRock.setOnClickListener { gameSimulator(Action.Rock) }

    }

    private fun gameHistoryList() {
        val intent = Intent(this, HistoryList::class.java)
        startActivity(intent)
    }

    //INSERT THE GAME INTO THE DB
    private fun gameIntoDB(game: Game) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }

    private fun winnerIs(game: Game): Game {
        game.result = when {
            game.computerPick == game.playerPick -> getString(R.string.draw_txt)
            game.computerPick == Action.Rock && game.playerPick == Action.Scissors -> getString(R.string.lose_txt)
            game.computerPick == Action.Scissors && game.playerPick == Action.Paper -> getString(R.string.lose_txt)
            game.computerPick == Action.Paper && game.playerPick == Action.Rock -> getString(R.string.lose_txt)
            else -> getString(R.string.win_txt)
        }

        return game
    }

    private fun gameSimulator(action: Action) {
        //RANDOMIZE AN INT
        val computerPick = (0..2).random()
        val computerAction = when(computerPick) {
            0 -> Action.Rock
            1 -> Action.Paper
            else -> Action.Scissors
            //else -> Action.Paper
        }

        var game = Game(null, "", Date(), computerAction, action)

        game = winnerIs(game)
        gameIntoDB(game)
        tvWin.text = game.result
        ivComputer.setImageDrawable(getDrawable(game.computerPick.drawableResId))
        ivPlayer.setImageDrawable(getDrawable(game.playerPick.drawableResId))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_show_game_history ->{
                gameHistoryList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
