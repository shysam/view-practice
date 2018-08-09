package cn.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.view.practice.PieView;

public class MainActivity extends AppCompatActivity {

    private PieView mPieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPieView = findViewById(R.id.pie);

        findViewById(R.id.btnFill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fill();
            }
        });
    }

    private void fill() {
        List<PieView.PieData> pies = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            PieView.PieData pieData = new PieView.PieData();
            pieData.setName("名字:" + i);
            pieData.setValue(i + 1);
            pies.add(pieData);
        }

        mPieView.setData(pies);

    }
}
