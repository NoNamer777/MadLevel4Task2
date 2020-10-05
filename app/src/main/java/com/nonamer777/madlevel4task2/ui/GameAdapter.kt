package com.nonamer777.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nonamer777.madlevel4task2.R
import com.nonamer777.madlevel4task2.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(
    
    private val games: List<Game>

): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun dataBind(game: Game) {
            itemView.txtGameResult.text = getGameResult(game)
            itemView.txtGameDate.text = game.date.toString()
            itemView.imgGameResultComputer.setImageResource(game.computerMove.img)
            itemView.imgGameResultYou.setImageResource(game.userMove.img)
        }
    }

    private fun getGameResult(game: Game): String {
        return when {
            game.won -> "You win!"
            game.draw -> "Draw"
            else -> "Computer wins!"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.dataBind(games[position])

    override fun getItemCount(): Int = games.size
}
