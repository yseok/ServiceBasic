package com.yuseok.android.servicebasic;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";

    IBinder mBinder = new MyBinder();

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "====onBind");
        // 액티비티에서 bindService()를 실행하면 호출됨
        // 리턴한 IBinder객체는 서비스와 클라이언트 사이의 인터페이스를 정의한다
        return  mBinder; // 바인더 객체를 리턴
    }
    // ------------------------------------------------------------

    public void prin(String value) {
        System.out.println(" 출력");
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "============onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "============onDestroy");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "intent ="+intent+", flags = "+flags+", startId = "+startId
        );

        Log.i(TAG,"=============onStartCommand" + flags);

        for(int i=0; i<100; i++) {
            System.out.println("서비스에서 동작중입니다=" + i);
            Toast.makeText(getBaseContext(), "서비스에서 동작중입니다=" + i, Toast.LENGTH_SHORT).show();
//
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        return super.onStartCommand(intent,flags,startId);
    }
}
