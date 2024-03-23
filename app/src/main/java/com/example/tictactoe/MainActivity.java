package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AppCompatButton btn;
    int turn=0;
    int count=0;
    boolean isGameOVer=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityMainBinding.inflate(getLayoutInflater());

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.getRoot());

        binding.restartBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            restartGame();
        }
    });

}

    public void btnClicked(View view){
        btn = findViewById(view.getId());
        if(btn.getText().toString().equals("") && isGameOVer == false){
            Log.d("checkbug", String.valueOf(isGameOVer));
            count++;
            if(turn == 0){
                btn.setTextColor(getColor(R.color.redText));
                btn.setText("X");
                turn=1;
            }else {
                btn.setTextColor(getColor(R.color.greyText));
                btn.setText("O");
                turn=0;
            }
        }
        checkWin();
    }

    private void checkWin() {
        if((binding.btn1.getText().toString() == binding.btn2.getText().toString() && binding.btn2.getText().toString() == binding.btn3.getText().toString() && binding.btn3.getText().toString() != "")){
            openDialog(binding.btn3.getText().toString());
        } else if ((binding.btn4.getText().toString() == binding.btn5.getText().toString() && binding.btn5.getText().toString() == binding.btn6.getText().toString() && binding.btn6.getText().toString() != "")) {
            openDialog(binding.btn6.getText().toString());
        } else if ((binding.btn7.getText().toString() == binding.btn8.getText().toString() && binding.btn8.getText().toString() == binding.btn9.getText().toString() && binding.btn9.getText().toString() != "")) {
            openDialog(binding.btn9.getText().toString());
        } else if ((binding.btn1.getText().toString() == binding.btn4.getText().toString() && binding.btn4.getText().toString() == binding.btn7.getText().toString() && binding.btn7.getText().toString() != "")) {
            openDialog(binding.btn7.getText().toString());
        } else if ((binding.btn2.getText().toString() == binding.btn5.getText().toString() && binding.btn5.getText().toString() == binding.btn8.getText().toString() && binding.btn8.getText().toString() != "")) {
            openDialog(binding.btn8.getText().toString());
        } else if ((binding.btn3.getText().toString() == binding.btn6.getText().toString() && binding.btn6.getText().toString() == binding.btn9.getText().toString() && binding.btn9.getText().toString() != "")) {
            openDialog(binding.btn9.getText().toString());
        } else if ((binding.btn1.getText().toString() == binding.btn5.getText().toString() && binding.btn5.getText().toString() == binding.btn9.getText().toString() && binding.btn9.getText().toString() != "")) {
            openDialog(binding.btn9.getText().toString());
        } else if ((binding.btn3.getText().toString() == binding.btn5.getText().toString() && binding.btn5.getText().toString() == binding.btn7.getText().toString() && binding.btn7.getText().toString() != "")) {
            openDialog(binding.btn7.getText().toString());
        } else if (count == 9) {
            openDialog("It's a");
        }
    }

    private void openDialog(String winner) {
        isGameOVer = true;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.winner_dialog);

        TextView winnerName = dialog.findViewById(R.id.winnerName);
        TextView tieOrWin = dialog.findViewById(R.id.tieOrWin);
        AppCompatButton playAgain = dialog.findViewById(R.id.playAgain);

        winnerName.setText(winner);
        if(winnerName.getText().toString().equals("It's a")){
            tieOrWin.setText("Tie");
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
                dialog.dismiss();
            }
        });

        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        dialog.show();
    }

    private void restartGame() {
        turn=0;
        count=0;
        isGameOVer=false;
        binding.btn1.setText("");
        binding.btn2.setText("");
        binding.btn3.setText("");
        binding.btn4.setText("");
        binding.btn5.setText("");
        binding.btn6.setText("");
        binding.btn7.setText("");
        binding.btn8.setText("");
        binding.btn9.setText("");
    }
}