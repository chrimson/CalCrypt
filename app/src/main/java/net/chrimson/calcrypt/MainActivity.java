package net.chrimson.calcrypt;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //region Variables
    private String op;
    private List<Long> values = new ArrayList<Long>();
    private String valueText;
    private Long result;
    private TextView display;
    private ScrollView scroller;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scroller = findViewById(R.id.scroller);
        display = findViewById(R.id.display);
        display.setTypeface(Typeface.MONOSPACE);

        op = "NON";
        valueText = "";
        result = 0L;

        //region All Button IDs
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
        Button btnPnt = findViewById(R.id.pnt);
        Button btnClr = findViewById(R.id.clr);
        Button btnAdd = findViewById(R.id.add);
        Button btnSub = findViewById(R.id.sub);
        Button btnMul = findViewById(R.id.mul);
        Button btnDiv = findViewById(R.id.div);
        Button btnMod = findViewById(R.id.mod);
        Button btnPow = findViewById(R.id.pow);
        Button btnFac = findViewById(R.id.fac);
        Button btnSqt = findViewById(R.id.sqt);
        Button btnGcd = findViewById(R.id.gcd);
        Button btnPhi = findViewById(R.id.phi);
        Button btnSam = findViewById(R.id.sam);
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
        btnPnt.setOnClickListener(view -> {
            if (!valueText.contains(".")) {
                valueText += ".";
                display.append(".");
            }
        });
        //endregion

        //region Operator Button Listeners
        btnAdd.setOnClickListener(view -> operateTwo("+"));
        btnSub.setOnClickListener(view -> operateTwo("-"));
        btnMul.setOnClickListener(view -> operateTwo("*"));
        btnDiv.setOnClickListener(view -> operateTwo("/"));
        btnPow.setOnClickListener(view -> operateTwo("^"));
        btnSqt.setOnClickListener(view -> operateOne("sqt"));
        btnFac.setOnClickListener(view -> operateOne("!"));
        btnPhi.setOnClickListener(view -> operateOne("phi"));
        btnMod.setOnClickListener(view -> operateTwo("mod"));
        btnGcd.setOnClickListener(view -> operateTwo("gcd"));
        btnSam.setOnClickListener(view -> operateMult("sam"));
        //endregion

        btnClr.setOnClickListener(view -> {
            String text = display.getText().toString();
            display.setText(text.substring(0, text.lastIndexOf("\n") + 1));

            values.clear();
            valueText = "";
            op = "NON";
            scroller.fullScroll(View.FOCUS_DOWN);
        });
        btnEnt.setOnClickListener(view -> {
            if (op != "NON") {
                if (op == "sam") {
                    values.add(Long.parseLong(valueText));
                    display.append("\n");

                    valueText = "";
                    scroller.fullScroll(View.FOCUS_DOWN);

                    switch (values.size()) {
                        case 1:
                            display.append("e = ");
                            break;
                        case 2:
                            display.append("m = ");
                            break;
                        case 3:
                            result = result(op);
                            display.append(String.valueOf(result));
                            display.append("\n");

                            op = "NON";
                            values.clear();
                            break;
                    }

                } else {
                    values.add(Long.parseLong(valueText));
                    display.append("\n");

                    result = result(op);
                    display.append(String.valueOf(result));
                    display.append("\n");

                    valueText = "";
                    op = "NON";
                    scroller.fullScroll(View.FOCUS_DOWN);
                }
            }
        });

        scroller.post(new Runnable() {
            @Override
            public void run() {
                scroller.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private void operateOne(String op) {
        if (this.op == "NON" && values.size() == 0) {
            this.op = op;

            if (valueText.length() > 0) {
                values.add(Long.parseLong(valueText));
                valueText = "";
            } else if (valueText.length() == 0) {
                values.add(result);
                display.append(String.valueOf(result));
            }
            display.append(" " + op + "\n");

            result = result(op);
            display.append(String.valueOf(result));
            display.append("\n");

            valueText = "";
            this.op = "NON";
            scroller.fullScroll(View.FOCUS_DOWN);
        }
    }

    private void operateTwo(String op) {
        if (this.op == "NON" && values.size() == 0) {
            this.op = op;

            if (valueText.length() > 0) {
                values.add(Long.parseLong(valueText));
                valueText = "";
            } else if (valueText.length() == 0) {
                values.add(result);
                display.append(String.valueOf(result));
            }

            display.append(" " + op + " ");
        }
    }

    private void operateMult(String op) {
        if (this.op == "NON" && values.size() == 0 && valueText.length() == 0) {
            this.op = op;
            display.append("x ^ e mod m OR sig(x) ^ b mod n\n");
            display.append("x = ");
        }
    }

    private Long result(String op) {
        Long temp = null;

        switch (op) {
            case "+":
                temp = values.get(0) + values.get(1);
                break;
            case "-":
                temp = values.get(0) - values.get(1);
                break;
            case "*":
                temp = values.get(0) * values.get(1);
                break;
            case "/":
                temp = values.get(0) / values.get(1);
                break;
            case "mod":
                temp = values.get(0) % values.get(1);
                break;
            case "^":
                temp = (long) Math.pow(values.get(0), values.get(1));
                break;
            case "!":
                temp = 1L;
                for (int factor = 2; factor <= values.get(0); factor++) {
                    temp *= factor;
                }
                break;
            case "sqt":
                temp = (long) Math.sqrt(values.get(0));
                break;
            case "gcd":
                temp = gcd(values.get(0), values.get(1));
                break;
            case "phi":
                temp = phi(values.get(0));
                break;
            case "sam":
                temp = sam(values.get(0), values.get(1), values.get(2));
                break;
        }

        values.clear();
        return temp;
    }

    private Long gcd(Long a, Long b) {
        Long r;
        Long t = 0L;
        t = a / b;
        Long bb = a % b;

        if (op == "gcd") {
            display.append(a + " = " + t + " x " + b + " + " + bb + "\n");
        }

        if (bb != 0) {
            r = gcd(b, bb);
        } else {
            r = b;
        }

        return r;
    }

    private Long phi(Long a) {
        Long p = 0L;
        for (Long n = 1L; n <= a - 1; n ++) {
            display.append(n + ": gcd(" + n + ", " + a + ") = " + gcd(n, a) + "\n");
            if (gcd(n, a) == 1) {
                p ++;
            }
        }

        return p;
    }

    private Long sam(Long x, Long e, Long m) {
        Long y = 1L;

        String b = Long.toBinaryString(e);
        display.append("e = " + b + "b\n");
        for (int n = 0; n < b.length(); n ++) {
            display.append("SQR y = (" + y + " ^ 2) mod " + m + " = " + (y * y) + "\n");
            y = (y * y) % m;

            if (b.charAt(n) == '1') {
                display.append("MUL y = (" + y + " * " +  x + ") mod " + m + " = " + (y * x) + "\n");
                y = (y * x) % m;
            }
        }

        return y;
    }

}