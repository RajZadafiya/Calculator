package com.example.calculatorpr1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updatetext(String strtoadd){
        String oldstr = display.getText().toString();
        int cursorposition = display.getSelectionStart();
        String leftstr = oldstr.substring(0,cursorposition);
        String rightstr = oldstr.substring(cursorposition);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strtoadd);
            display.setSelection(cursorposition+1);
        }else {
            display.setText(String.format("%S%S%S",leftstr,strtoadd,rightstr));
        }

    }

    public void zerobtn(View view){
        updatetext("0");

    }

    public void onebtn(View view){
        updatetext("1");
    }

    public void twobtn(View view){
        updatetext("2");

    }

    public void threebtn(View view){
        updatetext("3");

    }

    public void fourbtn(View view){
        updatetext("4");

    }

    public void fivebtn(View view){
        updatetext("5");
    }

    public void sixbtn(View view){
        updatetext("6");

    }

    public void sevenbtn(View view){
        updatetext("7");

    }

    public void eightbtn(View view){
        updatetext("8");
    }

    public void ninebtn(View view){
        updatetext("9");
    }

    public void clrbtn(View view){
        updatetext("");

    }

    public void parbtn(View view){

        int cursorpos = display.getSelectionStart();
        int openpar = 0;
        int closepar = 0;
        int textlen = display.getText().length();

        for (int i =0;i < cursorpos;i++){
            if (display.getText().toString().substring(i,i+1).equals("(")){
                openpar += 1;
            }
            if (display.getText().toString().substring(i,i+1).equals(")")){
                closepar += 1;
            }
        }

        if (openpar == closepar || display.getText().toString().substring(textlen - 1,textlen).equals("(")){
            updatetext("(");
        }
        else if (closepar < openpar && !display.getText().toString().substring(textlen - 1,textlen).equals("(")){
            updatetext(")");
        }
        display.setSelection(cursorpos +1);
    }

    public void expbtn(View view){
        updatetext("^");

    }

    public void divbtn(View view){
        updatetext("/");

    }

    public void mulbtn(View view){
        updatetext("*");

    }

    public void subbtn(View view){
        updatetext("-");

    }

    public void addbtn(View view){
        updatetext("+");

    }

    public void equalbtn(View view){
//        updatetext("=");

        String userexp = display.getText().toString();
//        userexp = userexp.replaceAll("÷","/");

        Expression exp = new Expression(userexp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());


    }

    public void plusminbtn(View view){
        updatetext("+/-");

    }

    public void dotbtn(View view){
        updatetext(".");

    }

    public void removebtn(View view){
        int cursorpos = display.getSelectionStart();
        int textlen = display.getText().length();

        if (cursorpos != 0 && textlen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorpos - 1,cursorpos,"");
            display.setText(selection);
            display.setSelection(cursorpos - 1);

        }
    }
}