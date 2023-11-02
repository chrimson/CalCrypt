package net.chrimson.calcrypt;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //region Variables
    private String op;
    private List<Long> values = new ArrayList<>();
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
        Button btnSam = findViewById(R.id.sam);
        Button btnPhi = findViewById(R.id.phi);
        Button btnOrd = findViewById(R.id.ord);
        Button btnMnv = findViewById(R.id.mnv);
        Button btnRsa = findViewById(R.id.rsa);
        Button btnCrt = findViewById(R.id.crt);
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
        btnSqt.setOnClickListener(view -> operateOne("sqt"));
        btnFac.setOnClickListener(view -> operateOne("!"));
        btnPhi.setOnClickListener(view -> operateOne("phi"));
        btnOrd.setOnClickListener(view -> operateOne("ord"));
        btnMnv.setOnClickListener(view -> operateOne("mnv"));
        btnCrt.setOnClickListener(view -> operateOne("crt"));
        btnAdd.setOnClickListener(view -> operateTwo("+"));
        btnSub.setOnClickListener(view -> operateTwo("-"));
        btnMul.setOnClickListener(view -> operateTwo("*"));
        btnDiv.setOnClickListener(view -> operateTwo("/"));
        btnPow.setOnClickListener(view -> operateTwo("^"));
        btnRsa.setOnClickListener(view -> operateTwo("rsa"));
        btnMod.setOnClickListener(view -> operateTwo("mod"));
        btnGcd.setOnClickListener(view -> operateTwo("gcd"));
        btnSam.setOnClickListener(view -> operateMulti("sam"));
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

    private void operateMulti(String op) {
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
            case "rsa":
                temp = rsa(values.get(0), values.get(1));
                break;
            case "crt":
                temp = crt(values.get(0));
                break;
            case "phi":
                temp = phi(values.get(0));
                break;
            case "ord":
                temp = ord(values.get(0));
                break;
            case "mnv":
                temp = mnv(values.get(0));
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

    private Long sam(Long x, Long e, Long m) {
        Long y = 1L;

        String b = Long.toBinaryString(e);
        display.append("e = " + b + "b\n");
        for (int n = 0; n < b.length(); n ++) {
            display.append("SQR y = (" + y + " ^ 2) mod " + m + ", " + (y * y) + "\n");
            y = (y * y) % m;

            if (b.charAt(n) == '1') {
                display.append("MUL y = (" + y + " * " +  x + ") mod " + m + ", " + (y * x) + "\n");
                y = (y * x) % m;
            }
        }

        return y;
    }

    private Long phi(Long n) {
        HashMap<Long, Integer> exp = new HashMap<>();

        for (int i = 2; i <= Math.sqrt(n); i ++) {
            int e = 0;
            while (n % i == 0) {
                if (op == "phi") {
                    display.append(i + " * ");
                }
                n = n / i;
                e ++;
                exp.put((long) i, e);
            }
        }

        Integer l = exp.get(n);
        if (l == null) {
            exp.put(n, 1);
        } else {
            exp.put(n, l ++);
        }
        if (op == "phi") {
            display.append(n + "\n");
        }

        Long p = 1L;
        for (Long f : exp.keySet()) {
            p = p * ((long) Math.pow((double) f, (double) exp.get(f)) -
                     (long) Math.pow((double) f, (double) exp.get(f) - 1));
            display.append("(" + f + "^" + exp.get(f) + " - " +
                                 f + "^" + (exp.get(f) - 1) + ") ");
        }
        display.append("\n");

        return p;
    }

    private Long mnv(Long a) {
        List<Long> z = new ArrayList<>();

        for (Long n = 1L; n <= a - 1; n++) {
            if (gcd(n, a) == 1) {
                z.add(n);
            }
        }

        for (Long nn : z) {
            for (Long inv : z) {
                if (nn * inv % a == 1) {
                    display.append("gcd(" + nn + ", " + a + ") = " + gcd(nn, a) + ", " + inv + "\n");
                    break;
                }
            }
        }

        return (long) z.size();
    }

    private Long ord(Long a) {
        List<Long> z = new ArrayList<>();
        List<Integer> o = new ArrayList<>();

        for (Long n = 1L; n <= a - 1; n ++) {
            display.append("gcd(" + n + ", " + a + ") = " + gcd(n, a) + "\n");
            if (gcd(n, a) == 1) {
                z.add(n);
            }
        }

        display.append("Multi Group ");
        for (Long i : z) {
            display.append(i + " ");
        }

        int c = z.size();

        display.append("\nPoss Orders ");
        for (int n = 1; n <= c; n ++) {
            if (c % n == 0) {
                o.add(n);
                display.append(n + " ");
            }
        }
        display.append("\n");

        for (Long e : z) {
            for (Integer x : o) {
                double m = Math.pow((double) e, (double) x) % a;
                if (m == 1) {
                    display.append("ord(" + e + ") = " + x + ", "
                                   + e + "^" + x + " mod " + a + "\n");
                    break;
                }
            }
        }

        display.append("Cardinality ");
        return (long) c;
    }

    private Long rsa(Long p, Long q) {
        Long[] pq = {p, q};
        boolean[] prime = {true, true};

        for (int i = 0; i <= 1; i ++) {
            for (int divisor = 2; divisor <= Math.sqrt(pq[i]); divisor ++) {
                if (pq[i] % divisor == 0) {
                    prime[i] = false;
                    break;
                }
            }
        }
        display.append("p prime " + prime[0] + "\n");
        display.append("q prime " + prime[1] + "\n");
        if (!prime[0] || !prime[1]) {
            return null;
        }

        Long n = pq[0] * pq[1];
        display.append("n = p * q = " + n + "\n");
        display.append("e and d are chosen MNVs in phi\n");
        display.append("encrypt y = x ^ e mod " + n + "\n");
        display.append("decrypt x = y ^ d mod " + n + "\n");
        display.append("phi(n) =");
        return phi(n);
    }

    private Long crt(Long c) {
        display.append("x = bi mod ni\n");
        display.append("Ni = N / ni       N = n1 n2 n3\n");
        display.append("Ni xi = 1 mod ni  (can mod reduce)\n");
        display.append("NNi xi = 1 mod ni\n");
        display.append("xi = mnv(ni)\n");
        display.append("                  x = E(bi Ni xi) mod N\n");
        return c;
    }

}