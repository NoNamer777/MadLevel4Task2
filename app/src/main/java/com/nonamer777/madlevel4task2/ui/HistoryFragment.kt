package com.nonamer777.madlevel4task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nonamer777.madlevel4task2.R
import com.nonamer777.madlevel4task2.model.Game
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass that shows the history of all games from the past.
 */
class HistoryFragment : Fragment() {

    companion object {
        /** An asynchronous scope in witch mostly requests to the Game Repository are handled. */
        private val mainScope = CoroutineScope(Dispatchers.Main)

        /** A list of Games indicating the history of Games that have happened in the past. */
        private val games = arrayListOf<Game>()

        /** The Adapter that transforms a Game object into a Game View. */
        val gameAdapter = GameAdapter(games)

        /** Requests all stored Games to be removed from the database. */
        fun clearHistory() {
            mainScope.launch {
                withContext(Dispatchers.IO) { MainActivity.gamesRepo.deleteAllGames() }

                // Update the list and RecyclerView
                getGameHistory()
            }
        }

        /** Requests all Games from the database. */
        private fun getGameHistory() {
            games.clear()

            mainScope.launch {
                games.addAll(withContext(Dispatchers.IO) { MainActivity.gamesRepo.getAllGames() })

                gameAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getGameHistory()
        initializeRecyclerView()
    }

    /** Configures the RecyclerView */
    private fun initializeRecyclerView() {
        rvGameHistory.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        rvGameHistory.adapter = gameAdapter
        rvGameHistory.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }
}
