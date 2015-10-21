package com.example.diorey_potestas.mastermindgame;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {

    Mastermind_Class p = new Mastermind_Class();
    int[] code_color = new int[8];
    //not yet working experiment ImageView[] boardSlotsById={(ImageView) findViewById(R.id.peg11),(ImageView) findViewById(R.id.peg12),(ImageView) findViewById(R.id.peg13),(ImageView) findViewById(R.id.peg14),(ImageView) findViewById(R.id.peg21),(ImageView) findViewById(R.id.peg22),(ImageView) findViewById(R.id.peg23),(ImageView) findViewById(R.id.peg24),(ImageView) findViewById(R.id.peg31),(ImageView) findViewById(R.id.peg32),(ImageView) findViewById(R.id.peg33),(ImageView) findViewById(R.id.peg34),(ImageView) findViewById(R.id.peg41),(ImageView) findViewById(R.id.peg42),(ImageView) findViewById(R.id.peg43),(ImageView) findViewById(R.id.peg44),(ImageView) findViewById(R.id.peg51),(ImageView) findViewById(R.id.peg52),(ImageView) findViewById(R.id.peg53),(ImageView) findViewById(R.id.peg54),(ImageView) findViewById(R.id.peg61),(ImageView) findViewById(R.id.peg62),(ImageView) findViewById(R.id.peg63),(ImageView) findViewById(R.id.peg64),(ImageView) findViewById(R.id.peg71),(ImageView) findViewById(R.id.peg72),(ImageView) findViewById(R.id.peg73),(ImageView) findViewById(R.id.peg74),(ImageView) findViewById(R.id.peg81),(ImageView) findViewById(R.id.peg82),(ImageView) findViewById(R.id.peg83),(ImageView) findViewById(R.id.peg84)};
    int tries = 8; //number of chances a player has given to break the hidden code
    int numPeg; //number of peg which corresponds to the number pegs being hidden
    int[] guess = new int[4];
    String gameOver = "You're not done yet!";
    ImageView boardSlot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPegCollection();
        setContentView(R.layout.activity_main);
    }

    public void setPegCollection() {
        code_color[0] = R.id.peg1;
        code_color[1] = R.id.peg2;
        code_color[2] = R.id.peg3;
        code_color[3] = R.id.peg4;
        code_color[4] = R.id.peg5;
        code_color[5] = R.id.peg6;
        code_color[6] = R.id.peg7;
        code_color[7] = R.id.peg8;
    }

    public void showPattern() {
        ImageView patternShow = (ImageView) findViewById(R.id.pattern1);
        View pattern = findViewById(p.getPattern()[0]);
        patternShow.setBackground(pattern.getBackground());

        patternShow = (ImageView) findViewById(R.id.pattern2);
        pattern = findViewById(p.getPattern()[1]);
        patternShow.setBackground(pattern.getBackground());

        patternShow = (ImageView) findViewById(R.id.pattern3);
        pattern = findViewById(p.getPattern()[2]);
        patternShow.setBackground(pattern.getBackground());

        patternShow = (ImageView) findViewById(R.id.pattern4);
        pattern = findViewById(p.getPattern()[3]);
        patternShow.setBackground(pattern.getBackground());
    }

    public void modifyBoard(int black, int white, int barLevel, int[] evalResult3) {
        String whiteCheck = String.valueOf(evalResult3[1]);
        String blackCheck = String.valueOf(evalResult3[0]);
        TextView blackEval = (TextView) findViewById(black);
        blackEval.setText(blackCheck);
        TextView whiteEval = (TextView) findViewById(white);
        whiteEval.setText(whiteCheck);
        LinearLayout bar = (LinearLayout) findViewById(barLevel);
        LinearLayout patternBar = (LinearLayout) findViewById(R.id.patternBar);
        bar.setBackground(patternBar.getBackground());
    }

    public void gameBoardLayout(int[] evalResult2) {
        switch (tries) {
            case 8:
                modifyBoard(R.id.black_code8, R.id.white_code8, R.id.bar8, evalResult2);
                break;
            case 7:
                modifyBoard(R.id.black_code7, R.id.white_code7, R.id.bar7, evalResult2);
                break;
            case 6:
                modifyBoard(R.id.black_code6, R.id.white_code6, R.id.bar6, evalResult2);
                break;
            case 5:
                modifyBoard(R.id.black_code5, R.id.white_code5, R.id.bar5, evalResult2);
                break;
            case 4:
                modifyBoard(R.id.black_code4, R.id.white_code4, R.id.bar4, evalResult2);
                break;
            case 3:
                modifyBoard(R.id.black_code3, R.id.white_code3, R.id.bar3, evalResult2);
                break;
            case 2:
                modifyBoard(R.id.black_code2, R.id.white_code2, R.id.bar2, evalResult2);
                break;
            case 1:
                modifyBoard(R.id.black_code1, R.id.white_code1, R.id.bar1, evalResult2);
                break;
            default:
                Toast toast = Toast.makeText(getApplicationContext(), gameOver + "\nWanna Play Again? Click New Game", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
        }
    }

    public void usePeg(View view) {
        if (tries > 0) {
            if (numPeg < 4) {
                getBoardPosition();
                boardSlot.setBackground(view.getBackground());
                guess[numPeg] = view.getId();
                numPeg++;
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "No more slots for new pegs.\n Only 4 pegs are accepted.\nPlease click ERASE if you want to remove one peg from your guess.", Toast.LENGTH_LONG);
                toast.show();
                toast.setGravity(Gravity.CENTER, 0, 0);
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Sorry the game is already over.\nClick NEW GAME to play again.", Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    public void checkGuess(View view) {
        int[] evalResult = p.evaluate(guess);
        TextView game_result = (TextView) findViewById(R.id.gameResult);
        gameBoardLayout(evalResult);
        if (evalResult[0] == 4) {
            showPattern();
            gameOver = "YOU WIN! Excellent Job!";
            Toast toast = Toast.makeText(getApplicationContext(), gameOver, Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.CENTER, 0, 0);
            game_result.setText("CONGRATULATIONS! You won the Game!");
            tries = 0;
            numPeg = -1;
        } else if ((evalResult[0] != 4) && (tries == 1)) {
            showPattern();
            gameOver = "YOU LOST! TRY AGAIN!";
            Toast toast = Toast.makeText(getApplicationContext(), gameOver, Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.CENTER, 0, 0);
            game_result.setText("SORRY! You lost the Game!");
            tries = 0;
        } else {
            tries--;
            numPeg = 0;
        }
    }

    public void erase(View view) {
        if ((tries > 0) || (gameOver == "You're not done yet!")) {
            if (numPeg > 0) {
                numPeg--;
                guess[numPeg] = -1;
                getBoardPosition();
                boardSlot.setBackgroundResource(R.drawable.key_peg1);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "No more guessed peg to delete.\nClick any colors from the choices on the left to make your guess.", Toast.LENGTH_LONG);
                toast.show();
                toast.setGravity(Gravity.CENTER, 0, 0);
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), gameOver + "\nWanna Play Again? Click New Game", Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    public void getBoardPosition() {
        switch (tries) {
            case 8:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg81);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg82);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg83);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg84);
                        break;
                    default:
                        break;
                }
                break;
            case 7:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg71);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg72);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg73);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg74);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg61);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg62);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg63);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg64);
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg51);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg52);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg53);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg54);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg41);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg42);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg43);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg44);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg31);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg32);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg33);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg34);
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg21);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg22);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg23);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg24);
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch (numPeg) {
                    case 0:
                        boardSlot = (ImageView) findViewById(R.id.peg11);
                        break;
                    case 1:
                        boardSlot = (ImageView) findViewById(R.id.peg12);
                        break;
                    case 2:
                        boardSlot = (ImageView) findViewById(R.id.peg13);
                        break;
                    case 3:
                        boardSlot = (ImageView) findViewById(R.id.peg14);
                        break;
                    default:
                        break;
                }
                break;
            default:
                boardSlot = null;
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.newGame) {
            p.generate_code_pattern(code_color, 4);
            tries = 8;
            numPeg = 0;
            guess = new int[4];
            setContentView(R.layout.board_page);
        }
        if (id == R.id.instructionPage) {
            setContentView(R.layout.instruction_page);
        }
        if (id == R.id.closeApp) {
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}
