package com.msaggik.fifthlessonconstructioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculationActivity extends AppCompatActivity {

    // поля
    private TextView output;
    private Button buttonBack, buttonNew;

    private int count = 0;
    private int costAll = 0;
    BuildingMaterial buildingMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        // привязка полей к разметке
        output = findViewById(R.id.output);
        buttonBack = findViewById(R.id.buttonBack);
        buttonNew = findViewById(R.id.buttonNew);

        // извлечение данных из намерения
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            int squareInt = bundle.getInt("keySquare");
            buildingMaterial = (BuildingMaterial) bundle.getSerializable(BuildingMaterial.class.getSimpleName());

            // расчёт (площадь поверхности (м*м) / (длину (м) * ширину (см) / 100))
            assert buildingMaterial != null;
            count = squareInt / ((buildingMaterial.getHeight() * buildingMaterial.getWidth())/100);
            if (squareInt % ((buildingMaterial.getHeight() * buildingMaterial.getWidth())/100) > 0) { // если при делении образуется остаток, то нужен ещё один рулон
                count++;
            }
            // расчёт стоимости
            costAll = count * buildingMaterial.getCost();

            // вывод на экран результатов расчёта
            String materialOutput = "";
            if (buildingMaterial instanceof Wallpaper) materialOutput = " рулонов обоев";
            else if (buildingMaterial instanceof Drywall) materialOutput = " пластин гипсокартона";

            output.setText("Для строительства нужно " + count + materialOutput + "\nИх стоимость " + costAll + " монет");
        }

        // обработка нажатия кнопок
        buttonBack.setOnClickListener(listener);
        buttonNew.setOnClickListener(listener);
    }

    private View.OnClickListener listener = view -> {

        Intent intent = null; // объявление намерения

        // инициализация намерения
        switch (view.getId()) {
            case R.id.buttonBack:
                if (buildingMaterial instanceof Wallpaper) intent = new Intent(getApplicationContext(), WallpaperActivity.class);
                else if (buildingMaterial instanceof Drywall) intent = new Intent(getApplicationContext(), DrywallActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // удаление всех активностей кроме той на которую происходит переход
                break;
            case R.id.buttonNew:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // перемещение нужной активности на вершину стека
                break;
        }
        startActivity(intent); // запуск намерения
    };
}