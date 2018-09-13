package com.example.juhwanlocal.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuActivity();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDataService();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String data = intent.getStringExtra("data");

            showToast("서비스로부터 전달받은 데이터 : " + command + ", " + data);
        }
    }

    public void showMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("name", "john");
        startActivityForResult(intent, 101);
    }

    public void startDataService() {
        Intent intent = new Intent(this, DataService.class);
        intent.putExtra("command", "show");
        intent.putExtra("data", "john");
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        processResult(intent);
    }

    public void processResult(Intent intent) {
        if (intent != null) {
            String name = intent.getStringExtra("data");
            showToast("메뉴로부터 전달받은 이름 : " + name);
        }
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
