package jp.ac.titech.itpro.sdl.startservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private BroadcastReceiver my_receiver;      // to receive from Service3
    private IntentFilter my_filter;             // to register receiver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        setContentView(R.layout.activity_main);

        my_receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d(TAG, "onReceive: " + action);

                if (action == null)
                    return;
                else if (action.contentEquals("My Custom Msg")) {
//                    Log.d(TAG, "Hurrah, I (MainActivity) received sth of action:" + action);
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        Toast.makeText(context,bundle.getString("Service3_payload"), Toast.LENGTH_LONG).show();
                        Log.d(TAG, bundle.getString("Service3_payload"));
                    }
                }
            }
        };

        my_filter = new IntentFilter();
        my_filter.addAction("My Custom Msg");
        this.registerReceiver(my_receiver,my_filter);
    }

    public void onClickTest1(View v) {
        Log.d(TAG, "onClickTest1 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service1.class);
        intent.putExtra(Service1.EXTRA_MYARG, "Hello, Service1");
        startService(intent);
    }

    public void onClickTest2(View v) {
        Log.d(TAG, "onClickTest2 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service2.class);
        intent.putExtra(Service2.EXTRA_MYARG, "Hello, Service2");
        startService(intent);
    }

    public void onClickTest3(View view) {
        Log.d(TAG, "onClickTest3 in " + Thread.currentThread());
        Intent intent = new Intent(this, Service3.class);
        intent.putExtra(Service3.EXTRA_MYARG,"Hello, Service3");
        startService(intent);
    }
}
