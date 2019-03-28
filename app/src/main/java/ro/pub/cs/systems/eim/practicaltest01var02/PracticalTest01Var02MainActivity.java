package ro.pub.cs.systems.eim.practicaltest01var02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PracticalTest01Var02MainActivity extends AppCompatActivity {

    static int CODE = 1;
    EditText number1 = null;
    EditText number2 = null;
    Button plusButton = null;
    Button minusButton = null;
    Button goNextButton = null;
    EditText resultField = null;
    private IntentFilter intentFilter = new IntentFilter();

    ButtonClickListener buttonClickListener = new ButtonClickListener();
    void makeToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case (R.id.plusButton):
                    try {
                        int nr1 = Integer.parseInt(number1.getText().toString());
                        int nr2 = Integer.parseInt(number2.getText().toString());
                        int result = nr1 + nr2;
                        resultField.setText(nr1 + " + " + nr2 + " = " + result);
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02Service.class);
                        intent.putExtra("firstNumber", nr1);
                        intent.putExtra("secondNumber", nr2);
                        getApplicationContext().startService(intent);
                    } catch (Exception e) {
                        makeToast("Not ok");
                        return;
                    }
                    break;
                case (R.id.minusButton):
                    try {
                        int nr1m = Integer.parseInt(number1.getText().toString());
                        int nr2m = Integer.parseInt(number2.getText().toString());
                        int resultm = nr1m - nr2m;
                        resultField.setText(nr1m + " + " + nr2m + " = " + resultm);
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02Service.class);
                        intent.putExtra("firstNumber", nr1m);
                        intent.putExtra("secondNumber", nr2m);
                        getApplicationContext().startService(intent);
                    } catch (Exception e) {
                        makeToast("Not ok");
                        return;
                    }
                    break;
                case (R.id.goNext):
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02SecondaryActivity.class);
                    intent.putExtra("operation", resultField.getText().toString());
                    startActivityForResult(intent, CODE);
                    break;
            }
        }
    }
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
           makeToast("Returned from secondary activity with code " + requestCode);

        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("DEBUG","Inside onsaveinstance");
        outState.putString("number1", number1.getText().toString());
        outState.putString("number2", number2.getText().toString());
        outState.putString("result", resultField.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("DEBUG","Inside savedInstanceState");
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("number1"))
                number1.setText(savedInstanceState.getString("number1"));
            if (savedInstanceState.containsKey("number2"))
                number2.setText(savedInstanceState.getString("number2"));
            if (savedInstanceState.containsKey("result"))
                resultField.setText(savedInstanceState.getString("result"));
            makeToast(savedInstanceState.getString("number1") + " " + savedInstanceState.getString("number2") + " " + savedInstanceState.getString("result"));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_main);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        goNextButton = findViewById(R.id.goNext);
        resultField = findViewById(R.id.result);

        plusButton.setOnClickListener(buttonClickListener);
        minusButton.setOnClickListener(buttonClickListener);
        goNextButton.setOnClickListener(buttonClickListener);
        intentFilter.addAction(Constants.actionType[0]);
        intentFilter.addAction(Constants.actionType[1]);
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }
}
