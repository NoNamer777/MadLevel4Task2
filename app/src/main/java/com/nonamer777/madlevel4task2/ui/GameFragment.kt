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
import java.util.*

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Updates the UI with data from the previous session if that exists.
        updateUI()

        // Set click event listeners for the different Move buttons.
        btnPaper.setOnClickListener { playGame(Move.PAPER) }

        btnRock.setOnClickListener { playGame(Move.ROCK) }

        btnScissors.setOnClickListener { playGame(Move.SCISSORS) }
    }

    /** Handles initiation of a game. */
    private fun playGame(playerMove: Move) {
        // Generate a random move for the computer.
        val computerMove = generateComputerMove()
        lastGame = Game(Date(), playerMove, computerMove)

        // When the computer and player both have the same move, handle a draw situation.
        if (playerMove == computerMove) {
            drawGame()
            return
        }

        // Handles losing or winning a game.
        when(playerMove) {
            Move.ROCK -> {
                if (computerMove == Move.SCISSORS) winGame()
                else loseGame()
            }
            Move.PAPER -> {
                if (computerMove == Move.ROCK) winGame()
                else loseGame()
            }
            Move.SCISSORS -> {
                if (computerMove == Move.PAPER) winGame()
                else loseGame()
            }
        }
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

    /** Handles playing a draw game. */
    private fun drawGame() {
        lastGame.draw = true
        lastGame.won = false

        totalDraws++

        updateUI()
    }

    /** Handles winning a game. */
    private fun winGame() {
        lastGame.won = true
        totalWins++

        updateUI()
    }

    /** Handles losing a game. */
    private fun loseGame() {
        totalLosses++

        updateUI()
    }

    /** Updates the UI based on the data from the last played match. */
    private fun updateUI() {
        textStats.text =
            String.format("Win: %d Draw: %d Lose: %d", totalWins, totalDraws, totalLosses)

        // Stop with updating the rest of the UI when there is no last match set
        if (!this::lastGame.isInitialized) return

        textResultGame.text = getMatchResult()
        imgResultYou.setImageResource(lastGame.playerMove.img)
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
}