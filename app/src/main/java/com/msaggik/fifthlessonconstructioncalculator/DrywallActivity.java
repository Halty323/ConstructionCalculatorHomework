package com.msaggik.fifthlessonconstructioncalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DrywallActivity extends AppCompatActivity {

    private EditText square;
    private EditText widthDrywall, heightDrywall, costDrywall;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gypsum_board);

        square = findViewById(R.id.square);
        widthDrywall = findViewById(R.id.widthWallpaper);
        heightDrywall = findViewById(R.id.heightWallpaper);
        costDrywall = findViewById(R.id.costWallpaper);
        button = findViewById(R.id.button);

        button.setOnClickListener(listener);
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int squareInt = Integer.parseInt(square.getText().toString());
            int width = Integer.parseInt(widthDrywall.getText().toString());
            int height = Integer.parseInt(heightDrywall.getText().toString());
            int cost = Integer.parseInt(costDrywall.getText().toString());

            Drywall drywall = new Drywall(width, height, cost);

            Intent intent = new Intent(getApplicationContext(), CalculationActivity.class);
            intent.putExtra("keySquare", squareInt);
            intent.putExtra(BuildingMaterial.class.getSimpleName(), drywall);

            startActivity(intent);
        }
    };
}