package com.nonamer777.madlevel4task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nonamer777.madlevel4task2.R
import com.nonamer777.madlevel4task2.model.Game
import com.nonamer777.madlevel4task2.model.Move
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass where the User can play a game of Rock, Paper, Scissors.
 */
class GameFragment : Fragment() {

    /** Total times the Player has played won a game. */
    private var totalWins = 0

    /** Total times the Player has played draw in a game. */
    private var totalDraws = 0

    /** Total times the Player has lost a game. */
    private var totalLosses = 0

    /** The last game that was initiated. */
    private lateinit var lastGame: Game

    /** An asynchronous scope in witch mostly requests to the Game Repository are handled. */
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Requests the necessary data from the database.
        getDataFromDatabase()

        // Set click event listeners for the different Move buttons.
        btnPaper.setOnClickListener { playGame(Move.PAPER) }

        btnRock.setOnClickListener { playGame(Move.ROCK) }

        btnScissors.setOnClickListener { playGame(Move.SCISSORS) }
    }

    /** Handles initiation of a game. */
    private fun playGame(userMove: Move) {
        // Generate a random move for the computer.
        val computerMove = generateComputerMove()

        // Create a new Game where the User presumably has lost the game.
        lastGame = Game(userMove, computerMove)

        // Checks the won / lose / draw state by comparing user and computer hands.
        when {
            // Rock beat scissors
            userMove == Move.ROCK && computerMove == Move.SCISSORS -> lastGame.won = true
            // Paper beat rock
            userMove == Move.PAPER && computerMove == Move.ROCK -> lastGame.won = true
            // Scissors beat paper
            userMove == Move.SCISSORS && computerMove == Move.PAPER -> lastGame.won = true
            // No one wins nor loses.
            userMove == computerMove -> lastGame.draw = true
        }
        persistLastGame()
        getDataFromDatabase()
    }

    /** Generates a simple random computer move. */
    private fun generateComputerMove(): Move {

        return when((1..3).random()) {
            1 -> Move.PAPER
            2 -> Move.ROCK
            3 -> Move.SCISSORS
            else -> Move.ROCK
        }
    }

    /** Updates the UI based on the data from the last played match. */
    private fun updateUI() {
        textStats.text =
            String.format("Win: %d Draw: %d Lose: %d", totalWins, totalDraws, totalLosses)

        // Stop with updating the rest of the UI when there is no last match set
        if (!this::lastGame.isInitialized) return

        textResultGame.text = getMatchResult()
        imgResultYou.setImageResource(lastGame.userMove.img)
        imgResultComputer.setImageResource(lastGame.computerMove.img)
    }

    /** Returns a text based on the result of the last match. */
    private fun getMatchResult(): String {
        return when {
            lastGame.won -> "You've Won!"
            lastGame.draw -> "You've neither won nor lost"
            else -> "You've lost"
        }
    }

    /** Gets all data necessary data from the database. */
    private fun getDataFromDatabase() {
        mainScope.launch {
            lastGame = withContext(Dispatchers.IO) { MainActivity.gamesRepo.getLastGame() }

            totalWins = withContext(Dispatchers.IO) { MainActivity.gamesRepo.getNumberOfWins() }

            totalDraws = withContext(Dispatchers.IO) { MainActivity.gamesRepo.getNumberOfDraws() }

            totalLosses = withContext(Dispatchers.IO) { MainActivity.gamesRepo.getNumberOfLosses() }

            // Updates the UI after the data has been retrieved.
            updateUI()
        }
    }

    /** Saves the last game that was played into the database. */
    private fun persistLastGame() {
        mainScope.launch {
            withContext(Dispatchers.IO) { MainActivity.gamesRepo.saveGame(lastGame) }
        }
    }
}