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

import com.google.android.material.switchmaterial.SwitchMaterial;

import fr.lomis.iut.m4101c2021_2022.R;
import fr.lomis.iut.m4101c2021_2022.utils.GameSettings;
import fr.lomis.iut.m4101c2021_2022.utils.Scores;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent entry_data = getIntent();
        if (!entry_data.hasExtra("pseudo")) {
            Toast.makeText(this, R.string.pseudo_not_defined, Toast.LENGTH_SHORT).show();
            this.finish();
        }
        String pseudo = entry_data.getStringExtra("pseudo");
        TextView connected_as_pseudo = findViewById(R.id.connected_as_pseudo);
        connected_as_pseudo.setText(pseudo);
        View.OnClickListener btn_start_game_OnClickListener = view -> {
            SwitchMaterial chillMode = findViewById(R.id.switch_chill_mode);
            final int btn_easy = R.id.btn_start_game_easy;
            final int btn_normal = R.id.btn_start_game_normal;
            final int btn_hard = R.id.btn_start_game_hard;
            final int btn_impossible = R.id.btn_start_game_impossible;
            switch (view.getId()) {
                case btn_easy:
                    if (chillMode.isActivated())
                        startGame(GameSettings.Chill_Easy);
                    else
                        startGame(GameSettings.Easy);
                    break;
                case btn_normal:
                    if (chillMode.isActivated())
                        startGame(GameSettings.Chill_Normal);
                    else
                        startGame(GameSettings.Normal);
                    break;
                case btn_hard:
                    if (chillMode.isActivated())
                        startGame(GameSettings.Chill_Hard);
                    else
                        startGame(GameSettings.Hard);
                    break;
                case btn_impossible:
                    if (chillMode.isActivated())
                        startGame(GameSettings.Chill_Impossible);
                    else
                        startGame(GameSettings.Impossible);
                    break;
                default:
            }
        };

        findViewById(R.id.btn_start_game_easy).setOnClickListener(btn_start_game_OnClickListener);
        findViewById(R.id.btn_start_game_normal).setOnClickListener(btn_start_game_OnClickListener);
        findViewById(R.id.btn_start_game_hard).setOnClickListener(btn_start_game_OnClickListener);
        findViewById(R.id.btn_start_game_impossible).setOnClickListener(btn_start_game_OnClickListener);
    }

    private void startGame(GameSettings settings) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("settings", settings);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_signout) {
            this.finish();
            return true;
        } else if (item.getItemId() == R.id.menu_high_scores) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.best_scores);
            builder.setIcon(R.drawable.ic_baseline_leaderboard_24);

            String stringBuilder = getString(R.string.best_scores) +
                    "\n" +
                    getString(R.string.easy) +
                    " : " +
                    Scores.instance.getPlayerEasy() +
                    "\n" +
                    getString(R.string.normal) +
                    " : " +
                    Scores.instance.getPlayerNormal();
            builder.setMessage(stringBuilder);
            builder.setNeutralButton(R.string.ok, (dialogInterface, i) -> {});
            builder.setCancelable(true);
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}