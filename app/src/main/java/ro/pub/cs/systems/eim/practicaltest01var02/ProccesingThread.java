package ro.pub.cs.systems.eim.practicaltest01var02;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProccesingThread extends Thread{
    private Context context = null;
    private int i = 0;
    private int sum;
    private int substraction;

    public ProccesingThread() {
    }

    public ProccesingThread(Context context,int firstNumber, int secondNumber) {
        this.context = context;
        this.sum = firstNumber + secondNumber;
        this.substraction = firstNumber - secondNumber;
    }

    @Override
    public void run() {
        super.run();
        Log.d("DEBUG", "Starting run method in thread");
        sendMessage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionType[i % 2]);
        intent.putExtra("message", substraction + " " + sum);
        context.sendBroadcast(intent);
        i++;
    }

    public void stopThread() {
    }
}
