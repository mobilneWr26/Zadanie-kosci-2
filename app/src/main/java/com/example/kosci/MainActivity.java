package com.example.kosci;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
    private boolean[] zablokowane = new boolean[5];
    private int[] wartosciKosci = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceViews[0] = findViewById(R.id.dice1);
        diceViews[1] = findViewById(R.id.dice2);
        diceViews[2] = findViewById(R.id.dice3);
        diceViews[3] = findViewById(R.id.dice4);
        diceViews[4] = findViewById(R.id.dice5);

        for(int i = 0; i < 5; i++){
            final int index = i;
            diceViews[i].setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            zablokowane[index] = true;

                            if(zablokowane[index] == true){
                                diceViews[index].setColorFilter(0x38000000);
                            }

                        }
                    }
            );
        }

        TextView wynikRzutuText = findViewById(R.id.wynikRzutuText);
        TextView wynikGryText = findViewById(R.id.wynikGryText);

        Button rzucButton = findViewById(R.id.rzucButton);
        Button resetButton = findViewById(R.id.resetButton);

        rzucButton.setOnClickListener(v -> {
            int suma = 0;

            for (int i = 0; i < 5; i++) {
                if(!zablokowane[i]){
                    wartosciKosci[i] = random.nextInt(6)+1;
                    int id = getResources().getIdentifier("k" + wartosciKosci[i], "drawable", getPackageName());
                    diceViews[i].setImageResource(id);
                }
                if(!zablokowane[i]){
                    suma += wartosciKosci[i];
                }
            }


            wynikGry += suma;

            wynikRzutuText.setText("Wynik tego losowania: " + suma);
            wynikGryText.setText("Wynik gry: " + wynikGry);
        });


        resetButton.setOnClickListener(v -> {
            for (ImageView img : diceViews)
                img.setImageResource(R.drawable.question);

            wynikGry = 0;
            for(int i = 0; i < 5; i++){
                zablokowane[i] = false;
                diceViews[i].clearColorFilter();
            }

            wynikRzutuText.setText("Wynik tego losowania: 0");
            wynikGryText.setText("Wynik gry: 0");
        });
    }
}
