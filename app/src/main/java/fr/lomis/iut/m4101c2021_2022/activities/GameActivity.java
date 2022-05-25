package fr.lomis.iut.m4101c2021_2022.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fr.lomis.iut.m4101c2021_2022.R;
import fr.lomis.iut.m4101c2021_2022.utils.Game;
import fr.lomis.iut.m4101c2021_2022.utils.GameCalcul;
import fr.lomis.iut.m4101c2021_2022.utils.GameSettings;

public class GameActivity extends AppCompatActivity {

    boolean cheater = false;
    Game game;
    TextView text_calcul;
    TextView text_score;
    TextView text_health;
    TextView text_result;
    TextView text_cheat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent input_data = getIntent();
        if (!input_data.hasExtra("settings")) {
            Toast.makeText(this, R.string.error_selected_level, Toast.LENGTH_SHORT).show();
            this.finish();
        }
        GameSettings settings = (GameSettings) input_data.getSerializableExtra("settings");
        game = new Game(settings);

        text_calcul = findViewById(R.id.text_calcul);
        text_score = findViewById(R.id.txt_score_value);
        text_health = findViewById(R.id.txt_health_value);
        text_result = findViewById(R.id.text_result);
        text_cheat = findViewById(R.id.txt_cheat);

        setKeyboard();

        text_score.setText(Integer.toString(game.getScore()));
        text_health.setText(Integer.toString(game.getCurrentLifeCount()));

        game.setHealthChangedListener(new_value -> text_health.setText(Integer.toString(new_value)));
        game.setOnScoreChangedListener(new_value -> text_score.setText(Integer.toString(new_value)));
        game.setOnGameOverListener(this::GameOver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NewCalcul();
    }

    public void NewCalcul() {
        try {
            GameCalcul calcul = game.generateNewCalcul();
            text_calcul.setText(calcul.toString());
            if (cheater)
                text_cheat.setText(Integer.toString(calcul.getResult()));
            //Timer.setTime(game.getSettings().getMax_time_in_seconds_per_calcul());
        } catch (ArithmeticException ex) {
            Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void GameOver(int final_score) {
        Toast.makeText(this, "Final score is " + final_score, Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_game_quit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.quit);
            builder.setIcon(R.drawable.ic_outline_highlight_off_24);
            builder.setMessage(R.string.really_quit);
            builder.setPositiveButton(R.string.quit, (dialogInterface, i) -> {
                this.finish();
            });
            builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                dialogInterface.cancel();
            });
            builder.setCancelable(true);
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNumberToUserInput(int number) {
        text_result.setText("" + text_result.getText() + number);
    }

    public void setKeyboard() {
        View.OnClickListener btn_keyboards_OnClickListener = view -> {
            final int btn_0 = R.id.button_0;
            final int btn_1 = R.id.button_1;
            final int btn_2 = R.id.button_2;
            final int btn_3 = R.id.button_3;
            final int btn_4 = R.id.button_4;
            final int btn_5 = R.id.button_5;
            final int btn_6 = R.id.button_6;
            final int btn_7 = R.id.button_7;
            final int btn_8 = R.id.button_8;
            final int btn_9 = R.id.button_9;
            switch (view.getId()) {
                case btn_0:
                    addNumberToUserInput(0);
                    break;
                case btn_1:
                    addNumberToUserInput(1);
                    break;
                case btn_2:
                    addNumberToUserInput(2);
                    break;
                case btn_3:
                    addNumberToUserInput(3);
                    break;
                case btn_4:
                    addNumberToUserInput(4);
                    break;
                case btn_5:
                    addNumberToUserInput(5);
                    break;
                case btn_6:
                    addNumberToUserInput(6);
                    break;
                case btn_7:
                    addNumberToUserInput(7);
                    break;
                case btn_8:
                    addNumberToUserInput(8);
                    break;
                case btn_9:
                    addNumberToUserInput(9);
                    break;
            }
        };
        findViewById(R.id.button_0).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_1).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_2).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_3).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_4).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_5).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_6).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_7).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_8).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_9).setOnClickListener(btn_keyboards_OnClickListener);
        findViewById(R.id.button_delete).setOnClickListener(this::deleteNumberFromUserInput);
        findViewById(R.id.button_delete).setOnLongClickListener(this::clearUserInput);
        findViewById(R.id.button_confirm).setOnClickListener(this::userValidCalcul);
    }

    private boolean clearUserInput(View view) {
        text_result.setText("");
        return true;
    }

    private void userValidCalcul(View view) {
        game.CompareCalcul(game.getCurrentCalcul(), getUserInput());
        clearUserInput(view);
        NewCalcul();
    }

    private int getUserInput() {
        String userInput = text_result.getText().toString();
        int userInputNumber = -1;
        try {
            userInputNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        return userInputNumber;
    }

    private void deleteNumberFromUserInput(View view) {
        CharSequence text = text_result.getText();
        if (text.length() <= 0) return;
        if (text.length() == 1)
            text_result.setText("");
        else
            text_result.setText(
                text.toString().toCharArray(),
                0,
                text.length()-1);
    }
}