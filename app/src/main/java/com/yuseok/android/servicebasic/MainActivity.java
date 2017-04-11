package com.yuseok.android.servicebasic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button btnStart, btnStop, btnBind, btnUnbind, btnCall;

    MyService bService; // 서비스 객체
    boolean isService = false; // 서비스중인 확인용

    ServiceConnection conn = new ServiceConnection() {
        // 서비스와 연결되는 순간 호출되는 함수
        @Override                                           // 서비스의 onBinder에서 리턴되는 값이 binder에 담겨온다.
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MyService.MyBinder mb = (MyService.MyBinder) binder;
            bService = mb.getService();
            isService = true;
        }

        // 서비스가 중단되거나 연결이 도중에 끊겼을 때 발생
        // onDestroy에서는 호출되지 않는다
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStop = (Button)findViewById(R.id.btnStop);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnBind = (Button) findViewById(R.id.btnBind);
        btnUnbind = (Button) findViewById(R.id.btnStart);
        btnCall = (Button) findViewById(R.id.btnCall);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
            }
        });

        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bService.mBinder
            }
        });


    }

    @Override
    protected void onDestroy() {

        // 서비스 종료

        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        super.onDestroy();
    }
}
