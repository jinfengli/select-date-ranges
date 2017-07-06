package com.kingfeng.select_date_ranges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kingfeng.select_date_ranges.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;

public class FirstActivity extends Activity {

    private String thisPeriodStart;
    private String thisPeriodEnd;
    private String samePeriodStart;
    private String samePeriodEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        String yesterdayDate = TimeUtil.getDateString(new Date().getTime() - TimeUtil.dayMills, "yyyy.MM.dd");
        thisPeriodStart = thisPeriodEnd = yesterdayDate;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastYearSameDate = calendar.getTime();
        String lastYearSameDay = TimeUtil.getDateString(lastYearSameDate.getTime(), "yyyy.MM.dd");
        samePeriodStart = samePeriodEnd =lastYearSameDay;
//        samePeriodStart = PreferenceUtils.getPrefString(this, account + "_" + Constants.SAME_START_DATE_KEY, lastYearSameDay);
//        samePeriodEnd = PreferenceUtils.getPrefString(this, account + "_" + Constants.SAME_END_DATE_KEY, lastYearSameDay);


        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  intent = new Intent(FirstActivity.this, MainActivity.class);
                intent.putExtra("thisPeriodStart", thisPeriodStart);
                intent.putExtra("thisPeriodEnd", thisPeriodEnd);
                intent.putExtra("samePeriodStart", samePeriodStart);
                intent.putExtra("samePeriodEnd", samePeriodEnd);

                startActivity(intent);
            }
        });
    }
}
