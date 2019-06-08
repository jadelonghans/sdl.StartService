package jp.ac.titech.itpro.sdl.startservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class Service3 extends IntentService {
    private final static String TAG = Service3.class.getSimpleName();
    public static String EXTRA_MYARG = "MYARG" ;
    private Intent my_intent;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name [i removed name] Used to name the worker thread, important only for debugging.
     */
    public Service3() {
        super(TAG);
    }

    //https://developer.android.com/guide/components/services#java

    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        Log.d(TAG, "onHandleIntent in " + Thread.currentThread());
        Log.d(TAG, "myarg = " + intent.getStringExtra(EXTRA_MYARG));
        try {
            Thread.sleep(5000); // 5 sec
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        my_intent = new Intent();
        my_intent.setAction("My Custom Msg");
        my_intent.putExtra("Service3_payload", "I'm Service3. Thanks for choosing me, MainActivity!");
        sendBroadcast(my_intent);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate in " + Thread.currentThread());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy in " + Thread.currentThread());
    }


}
