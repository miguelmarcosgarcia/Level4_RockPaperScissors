package com.example.rockpaperscissor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissor.model.Game
import kotlinx.android.synthetic.main.content_main.view.ivComputer
import kotlinx.android.synthetic.main.content_main.view.ivPlayer
import kotlinx.android.synthetic.main.content_main.view.tvWin
import kotlinx.android.synthetic.main.item_game_history.view.*

public class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_history, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvWin.text = game.result
            itemView.tvTime.text = game.time.toLocaleString()
            itemView.ivComputer.setImageDrawable(context.getDrawable(game.computerPick.drawableResId))
            itemView.ivPlayer.setImageDrawable(context.getDrawable(game.playerPick.drawableResId))
        }
    }
}