package net.chrimson.calcrypt;

import android.os.Bundle;
//import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private enum Op {
        ADD, SUB, MUL, DIV, MOD, POW, NON, GCD, PHI
    }
    private Op op;

    private double firstValue;
    private TextView display;
    private ScrollView scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scroller = findViewById(R.id.scroller);

        display = findViewById(R.id.display);

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnEqu = findViewById(R.id.equ);


        btn0.setOnClickListener(view -> display.append("0"));
        btn1.setOnClickListener(view -> display.append("1"));
        btn2.setOnClickListener(view -> display.append("2"));
        btn3.setOnClickListener(view -> display.append("3"));
        btn4.setOnClickListener(view -> display.append("4"));
        btn5.setOnClickListener(view -> display.append("5"));
        btn6.setOnClickListener(view -> display.append("6"));
        btn7.setOnClickListener(view -> display.append("7"));
        btn8.setOnClickListener(view -> display.append("8"));
        btn9.setOnClickListener(view -> display.append("9"));

        btnEqu.setOnClickListener(view -> {
            display.append("\n");
            scroller.fullScroll(View.FOCUS_DOWN);
        });

        scroller.post(new Runnable() {
            @Override
            public void run() {
                scroller.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}