package net.chrimson.calcrypt;

import android.os.Bundle;
//import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private enum Op {
        ADD, SUB, MUL, DIV, MOD, POW, NON, GCD, PHI
    }
    private Op op;

    private List<Double> values = new ArrayList<Double>();
    private String valueText;
    private TextView display;
    private ScrollView scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueText = "";
        op = Op.NON;

        scroller = findViewById(R.id.scroller);

        display = findViewById(R.id.display);

        //region Button Ids
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
        Button btnAdd = findViewById(R.id.add);
        Button btnEnt = findViewById(R.id.ent);
        //endregion

        //region Number Button Listeners
        btn0.setOnClickListener(view -> {
            valueText += "0";
            display.append("0");
        });
        btn1.setOnClickListener(view -> {
            valueText += "1";
            display.append("1");
        });
        btn2.setOnClickListener(view -> {
            valueText += "2";
            display.append("2");
        });
        btn3.setOnClickListener(view -> {
            valueText += "3";
            display.append("3");
        });
        btn4.setOnClickListener(view -> {
            valueText += "4";
            display.append("4");
        });
        btn5.setOnClickListener(view -> {
            valueText += "5";
            display.append("5");
        });
        btn6.setOnClickListener(view -> {
            valueText += "6";
            display.append("6");
        });
        btn7.setOnClickListener(view -> {
            valueText += "7";
            display.append("7");
        });
        btn8.setOnClickListener(view -> {
            valueText += "8";
            display.append("8");
        });
        btn9.setOnClickListener(view -> {
            valueText += "9";
            display.append("9");
        });
        //endregion

        btnAdd.setOnClickListener(view -> {
            op = Op.ADD;
            values.add(Double.parseDouble(valueText));
            display.append("+");
            valueText = "";
        });

        btnEnt.setOnClickListener(view -> {
            values.add(Double.parseDouble(valueText));

            valueText = "";
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