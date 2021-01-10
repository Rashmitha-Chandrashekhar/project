package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        findViewById(R.id.btnSet).setOnClickListener(this);
        findViewById(R.id.btnSet).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        EditText t1=findViewById(R.id.edText);
        TimePicker tip=findViewById(R.id.timePicker);

        //set notificationID and message
        Intent intent=new Intent(AlarmActivity.this,AlarmReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("message",t1.getText().toString());

        PendingIntent alarmIntent= PendingIntent.getBroadcast(
                AlarmActivity.this,0,intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

        switch(v.getId()){
            case R.id.btnSet:
                //set alarm
                int hour=tip.getCurrentHour();
                int minute=tip.getCurrentMinute();

                //create time
                Calendar startTime=Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime=startTime.getTimeInMillis();

                //set alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnCancel:
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}