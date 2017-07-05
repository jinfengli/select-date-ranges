package com.kingfeng.select_date_ranges;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kingfeng.select_date_ranges.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private TextView tvSelectDate;
    private TextView tvThisPeriod; // 本期
    private TextView tvSamePeriod; // 同期

    private RelativeLayout rlThisPeriod;
    private RelativeLayout rlSamePeriod;

    private long startThisDate;  // 本期筛选的开始时间
    private long endThisDate;  // 本期筛选的结束时间
    private long startSameDate; // 同期筛选的开始时间
    private long endSameDate;  // 同期筛选的结束时间
    private int betweenDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        init();
        setListener();
    }

    protected void initViews() {
        tvSelectDate = (TextView) findViewById(R.id.tv_select_period);
        tvThisPeriod = (TextView) findViewById(R.id.tv_this_period);
        tvSamePeriod = (TextView) findViewById(R.id.tv_same_period);
        rlThisPeriod = (RelativeLayout) findViewById(R.id.rl_this_period);
        rlSamePeriod = (RelativeLayout) findViewById(R.id.rl_same_period);
    }


    protected void setListener() {
        tvSelectDate.setOnClickListener(this);
        rlThisPeriod.setOnClickListener(this);
        rlSamePeriod.setOnClickListener(this);
    }

    protected void init() {
        tvThisPeriod.setText(String.format(getResources().getString(R.string.selected_times),
                getIntent().getStringExtra("thisPeriodStart"),
                getIntent().getStringExtra("thisPeriodEnd")));
        tvSamePeriod.setText(String.format(getResources().getString(R.string.selected_times),
                getIntent().getStringExtra("samePeriodStart"),
                getIntent().getStringExtra("samePeriodEnd")));

        try {
            startThisDate  = stringToLong(getIntent().getStringExtra("thisPeriodStart"),"yyyy.MM.dd");
            endThisDate  = stringToLong(getIntent().getStringExtra("thisPeriodEnd"),"yyyy.MM.dd") + TimeUtil.dayMills -1 ;
            startSameDate  = stringToLong(getIntent().getStringExtra("samePeriodStart"),"yyyy.MM.dd");
            endSameDate  = stringToLong(getIntent().getStringExtra("samePeriodEnd"),"yyyy.MM.dd") + TimeUtil.dayMills -1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showSelectDatesDialog(final int selectViewId) {
        DateRangeDialog mdialog = new DateRangeDialog(this, R.style.BottomDialog);
        if (selectViewId == R.id.rl_this_period) {
            mdialog.setDialogTitle(getString(R.string.select_this_period));
        } else if(selectViewId == R.id.rl_same_period) {
            mdialog.setDialogTitle(getString(R.string.select_same_period));
        }
        mdialog.show();
        try {
            if (selectViewId == R.id.rl_this_period) {
                mdialog.setSelectDate(startThisDate, endThisDate);
            } else if (selectViewId == R.id.rl_same_period) {
                mdialog.setSelectDate(startSameDate, endSameDate);
                mdialog.setBetweenDays(betweenDays);
                mdialog.setEndDateTag(true);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mdialog.setDateRangeSelectedListener(new DateRangeDialog.DateRangeSelectedListener() {
            @Override
            public void onDateSelect(long start, long end) {
                if (selectViewId == R.id.rl_this_period) {
                    if(startThisDate == start && endThisDate == end) {
                        // 本期时间没变化，同期时间不清空
                    } else {
                        startThisDate = start;
                        endThisDate = end;
                        tvSamePeriod.setText("");
                        // 同期清空后 同期开始和结束时间需要置为 当前时间的前一天
                        startSameDate = TimeUtil.getCurrentDayToMills() - TimeUtil.dayMills;;
                        endSameDate = TimeUtil.getCurrentDayToMills() - TimeUtil.dayMills;
                    }

                    tvThisPeriod.setText(String.format(getResources().getString(R.string.selected_times),
                            TimeUtil.getDateString(startThisDate, "yyyy.MM.dd"),
                            TimeUtil.getDateString(endThisDate, "yyyy.MM.dd")));
                } else if (selectViewId == R.id.rl_same_period) {
                    startSameDate = start;
                    endSameDate = end;
                    tvSamePeriod.setText(String.format(getResources().getString(R.string.selected_times),
                            TimeUtil.getDateString(startSameDate, "yyyy.MM.dd"),
                            TimeUtil.getDateString(endSameDate, "yyyy.MM.dd")));
                }
            }
        });
    }

    public static Date stringToDate(String strTime, String formatType) throws ParseException {
        // strTime的时间格式必须要与formatType的时间格式相同
        return new SimpleDateFormat(formatType).parse(strTime);
    }

    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType); // strTime格式和formatType格式必须相同.
        if (date == null) {
            return 0;
        }
        return dateToLong(date);
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_select_period) {
            if(TextUtils.isEmpty(tvSamePeriod.getText())) {
                Toast.makeText(MainActivity.this, getString(R.string.please_select_same_period), Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("startThisDate", TimeUtil.getDateString(startThisDate, "yyyy.MM.dd"));
                intent.putExtra("endThisDate", TimeUtil.getDateString(endThisDate, "yyyy.MM.dd"));
                intent.putExtra("startSameDate", TimeUtil.getDateString(startSameDate, "yyyy.MM.dd"));
                intent.putExtra("endSameDate", TimeUtil.getDateString(endSameDate, "yyyy.MM.dd"));
                setResult(10, intent);
                MainActivity.this.finish();
            }
        } else if (view.getId() == R.id.rl_this_period) {
            showSelectDatesDialog(view.getId());
        } else if (view.getId() == R.id.rl_same_period) {
            betweenDays = (int) ((endThisDate - startThisDate) / 86400000);
            Log.d(TAG, "this select days:" + betweenDays);
            showSelectDatesDialog(view.getId());
        }
    }
}
