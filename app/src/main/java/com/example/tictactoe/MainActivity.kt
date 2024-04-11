package com.example.tictactoe

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btn: AppCompatButton
    private var turn = 0
    private var count = 0
    private var isGameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.restartBtn.setOnClickListener {
            restartGame()
        }
    }

    fun btnClicked(view: View) {
        btn = findViewById(view.id)
        if (btn.text.toString() == "" && !isGameOver) {
            Log.d("checkbug", isGameOver.toString())
            count++
            if (turn == 0) {
                btn.setTextColor(getColor(R.color.redText))
                btn.text = "X"
                turn = 1
            } else {
                btn.setTextColor(getColor(R.color.greyText))
                btn.text = "O"
                turn = 0
            }
        }
        checkWin()
    }

    private fun checkWin() {
        if (binding.btn1.text.toString() == binding.btn2.text.toString() &&
            binding.btn2.text.toString() == binding.btn3.text.toString() &&
            binding.btn3.text.toString() != ""
        ) {
            openDialog(binding.btn3.text.toString())
        } else if (binding.btn4.text.toString() == binding.btn5.text.toString() &&
            binding.btn5.text.toString() == binding.btn6.text.toString() &&
            binding.btn6.text.toString() != ""
        ) {
            openDialog(binding.btn6.text.toString())
        } else if (binding.btn7.text.toString() == binding.btn8.text.toString() &&
            binding.btn8.text.toString() == binding.btn9.text.toString() &&
            binding.btn9.text.toString() != ""
        ) {
            openDialog(binding.btn9.text.toString())
        } else if (binding.btn1.text.toString() == binding.btn4.text.toString() &&
            binding.btn4.text.toString() == binding.btn7.text.toString() &&
            binding.btn7.text.toString() != ""
        ) {
            openDialog(binding.btn7.text.toString())
        } else if (binding.btn2.text.toString() == binding.btn5.text.toString() &&
            binding.btn5.text.toString() == binding.btn8.text.toString() &&
            binding.btn8.text.toString() != ""
        ) {
            openDialog(binding.btn8.text.toString())
        } else if (binding.btn3.text.toString() == binding.btn6.text.toString() &&
            binding.btn6.text.toString() == binding.btn9.text.toString() &&
            binding.btn9.text.toString() != ""
        ) {
            openDialog(binding.btn9.text.toString())
        } else if (binding.btn1.text.toString() == binding.btn5.text.toString() &&
            binding.btn5.text.toString() == binding.btn9.text.toString() &&
            binding.btn9.text.toString() != ""
        ) {
            openDialog(binding.btn9.text.toString())
        } else if (binding.btn3.text.toString() == binding.btn5.text.toString() &&
            binding.btn5.text.toString() == binding.btn7.text.toString() &&
            binding.btn7.text.toString() != ""
        ) {
            openDialog(binding.btn7.text.toString())
        } else if (count == 9) {
            openDialog("It's a")
        }
    }

    private fun openDialog(winner: String) {
        isGameOver = true
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.winner_dialog)

        val winnerName = dialog.findViewById<TextView>(R.id.winnerName)
        val tieOrWin = dialog.findViewById<TextView>(R.id.tieOrWin)
        val playAgain = dialog.findViewById<AppCompatButton>(R.id.playAgain)

        winnerName.text = winner
        if (winnerName.text.toString() == "It's a") {
            tieOrWin.text = "Tie"
        }

        playAgain.setOnClickListener {
            restartGame()
            dialog.dismiss()
        }

        val window = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        dialog.show()
    }

    private fun restartGame() {
        turn = 0
        count = 0
        isGameOver = false
        with(binding) {
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""
            btn4.text = ""
            btn5.text = ""
            btn6.text = ""
            btn7.text = ""
            btn8.text = ""
            btn9.text = ""
        }
    }
}
