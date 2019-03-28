package ro.pub.cs.systems.eim.practicaltest01var02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var02SecondaryActivity extends AppCompatActivity {

    EditText label = null;
    Button correctButton = null;
    Button incorrectButton = null;

    SecondButtonListener secondButtonListener = new SecondButtonListener();
    private class SecondButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.correctButton):
                    setResult(RESULT_OK,null);
                    break;
                case (R.id.incorrectButton):
                    setResult(RESULT_CANCELED,null);
            }
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_secondary);

        label = findViewById(R.id.resultFromFirst);
        correctButton = findViewById(R.id.correctButton);
        incorrectButton = findViewById(R.id.incorrectButton);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("operation"))
            label.setText(String.valueOf(intent.getExtras().getString("operation")));

        correctButton.setOnClickListener(secondButtonListener);
        incorrectButton.setOnClickListener(secondButtonListener);

    }
}
