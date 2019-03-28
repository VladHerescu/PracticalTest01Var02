package ro.pub.cs.systems.eim.practicaltest01var02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class PracticalTest01Var02Service extends Service {
    ProccesingThread proccesingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int first = intent.getExtras().getInt("firstNumber");
        int second = intent.getExtras().getInt("secondNumber");
        Log.d("DEBUG","Inside onstartcommand of service");
        proccesingThread = new ProccesingThread(this.getApplicationContext(),first,second);
        proccesingThread.start();

        return Service.START_REDELIVER_INTENT;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        proccesingThread.stopThread();
        super.onDestroy();
    }
}
