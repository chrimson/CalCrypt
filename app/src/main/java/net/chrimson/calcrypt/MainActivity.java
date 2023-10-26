package net.chrimson.calcrypt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private enum Op {
        ADD, SUB, MUL, DIV, MOD, POW, NON
    }
    private Op op;

    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);

        Button button0 = findViewById(R.id.btn0);
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        Button button4 = findViewById(R.id.btn4);
        Button button5 = findViewById(R.id.btn5);
        Button button6 = findViewById(R.id.btn6);
        Button button7 = findViewById(R.id.btn7);
        Button button8 = findViewById(R.id.btn8);
        Button button9 = findViewById(R.id.btn9);

        Button buttonAdd = findViewById(R.id.add);
        Button buttonSub = findViewById(R.id.subtract);
        Button buttonDivide = findViewById(R.id.division);
        Button buttonDot = findViewById(R.id.btnPoint);
        Button buttonMultiply = findViewById(R.id.multiply);
        Button buttonClear = findViewById(R.id.clear);
        Button buttonPower = findViewById(R.id.power);
        Button buttonEqual = findViewById(R.id.equal);
        Button buttonModulo = findViewById(R.id.modulo);

        button0.setOnClickListener(view -> inputDisplay.setText(String.format("%s0", inputDisplay.getText())));
        button1.setOnClickListener(view -> inputDisplay.setText(String.format("%s1", inputDisplay.getText())));
        button2.setOnClickListener(view -> inputDisplay.setText(String.format("%s2", inputDisplay.getText())));
        button3.setOnClickListener(view -> inputDisplay.setText(String.format("%s3", inputDisplay.getText())));
        button4.setOnClickListener(view -> inputDisplay.setText(String.format("%s4", inputDisplay.getText())));
        button5.setOnClickListener(view -> inputDisplay.setText(String.format("%s5", inputDisplay.getText())));
        button6.setOnClickListener(view -> inputDisplay.setText(String.format("%s6", inputDisplay.getText())));
        button7.setOnClickListener(view -> inputDisplay.setText(String.format("%s7", inputDisplay.getText())));
        button8.setOnClickListener(view -> inputDisplay.setText(String.format("%s8", inputDisplay.getText())));
        button9.setOnClickListener(view -> inputDisplay.setText(String.format("%s9", inputDisplay.getText())));

        buttonAdd.setOnClickListener(view -> {
            allCalculations();
            op = Op.ADD;
            outputDisplay.setText(String.format("%s+", decimalFormat.format(firstValue)));
            inputDisplay.setText(null);
        });

        buttonSub.setOnClickListener(view -> {
            allCalculations();
            op = Op.SUB;
            outputDisplay.setText(String.format("%s-", decimalFormat.format(firstValue)));
            inputDisplay.setText(null);
        });

        buttonMultiply.setOnClickListener(view -> {
            allCalculations();
            op = Op.MUL;
            outputDisplay.setText(String.format("%sx", decimalFormat.format(firstValue)));
            inputDisplay.setText(null);
        });

        buttonDivide.setOnClickListener(view -> {
            allCalculations();
            op = Op.DIV;
            outputDisplay.setText(String.format("%s÷", decimalFormat.format(firstValue)));
            inputDisplay.setText(null);
        });

        buttonModulo.setOnClickListener(view -> {
            allCalculations();
            op = Op.MOD;
            //noinspection SpellCheckingInspection
            outputDisplay.setText(String.format("%smod", decimalFormat.format(firstValue)));
            inputDisplay.setText(null);
        });

        buttonPower.setOnClickListener(view -> {
            allCalculations();
            op = Op.POW;
            outputDisplay.setText(String.format("%s^", decimalFormat.format(firstValue)));
            inputDisplay.setText(null);
        });

        buttonDot.setOnClickListener(view -> inputDisplay.setText(String.format("%s.", inputDisplay.getText())));

        buttonClear.setOnClickListener(view -> {
            if (inputDisplay.getText().length() > 0) {
                CharSequence currentText = inputDisplay.getText();
                inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
            } else {
                firstValue = Double.NaN;
                secondValue = Double.NaN;
                inputDisplay.setText("");
                outputDisplay.setText("");
            }
        });

        buttonEqual.setOnClickListener(view -> {
            allCalculations();
            outputDisplay.setText(decimalFormat.format(firstValue));
            firstValue = Double.NaN;
            op = Op.NON;
        });
    }

    private void allCalculations(){
        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(inputDisplay.getText().toString());
            inputDisplay.setText(null);

            switch (op) {
                case ADD:
                    firstValue = this.firstValue + secondValue;
                    break;
                case SUB:
                    firstValue = this.firstValue - secondValue;
                    break;
                case MUL:
                    firstValue = this.firstValue * secondValue;
                    break;
                case DIV:
                    firstValue = this.firstValue / secondValue;
                    break;
                case MOD:
                    firstValue = this.firstValue % secondValue;
                    break;
                case POW:
                    firstValue = Math.pow(this.firstValue, secondValue);
                    break;
            }
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception ignored) {

            }
        }
    }
}