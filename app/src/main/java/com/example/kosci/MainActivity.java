package com.example.kosci;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kosci.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView[] diceViews = new ImageView[5];
    private Random random = new Random();
    private int wynikGry = 0;
    private ListView listView;
    private ArrayList<String> listaMozliwosci;
    private ArrayAdapter arrayAdapterListaMozliwosci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceViews[0] = findViewById(R.id.dice1);
        diceViews[1] = findViewById(R.id.dice2);
        diceViews[2] = findViewById(R.id.dice3);
        diceViews[3] = findViewById(R.id.dice4);
        diceViews[4] = findViewById(R.id.dice5);

        TextView wynikRzutuText = findViewById(R.id.wynikRzutuText);
        TextView wynikGryText = findViewById(R.id.wynikGryText);

        Button rzucButton = findViewById(R.id.rzucButton);
        Button resetButton = findViewById(R.id.resetButton);

        rzucButton.setOnClickListener(v -> {
            int suma = 0;

            for (int i = 0; i < 5; i++) {
                int liczba = random.nextInt(6) + 1;
                suma += liczba;

                // ustawienie grafiki
                int id = getResources().getIdentifier("k" + liczba, "drawable", getPackageName());
                diceViews[i].setImageResource(id);
            }


            wynikGry += suma;

            wynikRzutuText.setText("Wynik tego losowania: " + suma);
            wynikGryText.setText("Wynik gry: " + wynikGry);
        });


        resetButton.setOnClickListener(v -> {
            for (ImageView img : diceViews)
                img.setImageResource(R.drawable.question);

            wynikGry = 0;

            wynikRzutuText.setText("Wynik tego losowania: 0");
            wynikGryText.setText("Wynik gry: 0");
        });
    }
}
