package com.kingfeng.select_date_ranges;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingfeng.select_date_ranges.timessquare.CalendarCellDecorator;
import com.kingfeng.select_date_ranges.timessquare.CalendarPickerView;
import com.kingfeng.select_date_ranges.timessquare.CalendarRowView;
import com.kingfeng.select_date_ranges.timessquare.DefaultDayViewAdapter;
import com.kingfeng.select_date_ranges.timessquare.HBAppCalendarCellDecorator;
import com.kingfeng.select_date_ranges.util.TimeUtil;
import com.kingfeng.select_date_ranges.util.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * TODO: 日期范围选择dialog
 *
 * @author lijf
 * @version 1.0.0
 */
public class DateRangeDialog extends Dialog implements View.OnClickListener, CalendarPickerView.CellClickInterceptor,
        CalendarPickerView.OnDateSelectedListener,
        CalendarPickerView.OnInvalidDateSelectedListener,
        CalendarPickerView.DateSelectableFilter {
    private static final String TAG = "DateRangeDialog";

    protected Context context;
    private CalendarPickerView calendarPickerView;
    private DateRangeSelectedListener mDateRangeSelectedListener;
    private ArrayList<Date> mDates;
    private CalendarPickerView.FluentInitializer fluentInitializer;
    private String dialogTitle;

    private int betweenDays;
    private boolean isEndDateTag = false;

    public DateRangeDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    public boolean onCellClicked(Date date) {
        Log.e(TAG, new SimpleDateFormat("yyyy-MM-dd").format(date));

        if (isEndDateTag()) {
            calendarPickerView.clearHighlightedDates();

            ArrayList<Date> myDates = new ArrayList<>();
            myDates.add(date);
            long betweenMillis = getBetweenDays() * TimeUtil.dayMills;
            myDates.add(new Date(date.getTime() + betweenMillis));
            fluentInitializer.withSelectedDates(myDates);

            return true;
        }

        return false;
    }

    @Override
    public void onDateSelected(Date date) {
//        calendarPickerView.setCustomDayView(new DefaultDayViewAdapter());

        long start = calendarPickerView.getSelectedDates().get(0).getTime();
        if (date.getTime() - start > 4 * TimeUtil.dayMills) {
            ToastUtil.toastL(context, context.getString(R.string.select_no_more_5_days));
            // 把选中的日期范围给清掉
            calendarPickerView.clearSelectedRangeDates();
        } /*else {
            calendarPickerView.setCustomDayView(new DefaultDayViewAdapter());
        }*/
    }

    @Override
    public void onDateUnselected(Date date) {

    }

    @Override
    public void onInvalidDateSelected(Date date) {
//        ToastUtil.toastL(context, "click valid" + new SimpleDateFormat("yyyy-MM-dd").format(date));
        // 不可选中的日期
    }

    @Override
    public boolean isDateSelectable(Date date) {
        if (date.getTime() > TimeUtil.getCurrentDayMills() - TimeUtil.dayMills) {
            return false;
        }
//        return false;
        return true;
    }

    public interface DateRangeSelectedListener {
        void onDateSelect(long start, long end);
    }

    public void setDateRangeSelectedListener(DateRangeSelectedListener dateRangeSelectedListener) {
        this.mDateRangeSelectedListener = dateRangeSelectedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_content_normal, null);
        setContentView(contentView);

        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = context.getResources().getDisplayMetrics().heightPixels * 3 / 4;
        contentView.setLayoutParams(layoutParams);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

        calendarPickerView = (CalendarPickerView) findViewById(R.id.calendar_view);

        CalendarRowView rowView = (CalendarRowView) findViewById(R.id.calendar_head_row_view);
        rowView.setIsHeaderRow(true); // 第一行 星期栏

        ImageView ivClose = (ImageView) findViewById(R.id.iv_close);
        TextView tvSelectPeriodDialog = (TextView) findViewById(R.id.tv_select_period_sure);
        TextView tvCalendarTitle = (TextView) findViewById(R.id.tv_calendar_dialog_title);

        tvCalendarTitle.setText(getDialogTitle());
        tvSelectPeriodDialog.setOnClickListener(this);
        ivClose.setOnClickListener(this);

        setCalendarPickerViewListener();
        initCalendar();
    }

    private void setCalendarPickerViewListener() {
        calendarPickerView.setCellClickInterceptor(this);
        calendarPickerView.setOnDateSelectedListener(this);
        calendarPickerView.setOnInvalidDateSelectedListener(this);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);

        calendarPickerView.setDateSelectableFilter(this);
    }

    /**
     * 初始化日历, 默认选中的是昨日
     */
    private void initCalendar() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        mDates = new ArrayList<>();
        start.add(Calendar.DATE, -1);
        mDates.add(start.getTime());

        end.add(Calendar.DATE, -1);
        mDates.add(end.getTime());
        selectedDates(mDates);
    }

    /**
     * 设置默认进入时选中的时间
     */
    public void setSelectDate(long startMillsecond, long endMillsecond) throws ParseException {
        mDates.clear();
        mDates.add(convertTime(startMillsecond));
        mDates.add(convertTime(endMillsecond));
        selectedDates(mDates);
    }

    private Date convertTime(long orgMillseconds) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = format.format(new Date(orgMillseconds));
        return format.parse(strDate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_select_period_sure:
                List<Date> mSelectDate = calendarPickerView.getSelectedDates();
                if (mSelectDate.size() <= 1) {
                    ToastUtil.toastL(context, context.getString(R.string.please_select_same_period));
                } else if (mSelectDate.size() > 1 && mSelectDate.size() <= 5) {
                    long start = mSelectDate.get(0).getTime();
                    long end = mSelectDate.get(mSelectDate.size() - 1).getTime() + TimeUtil.dayMills - 1;
                    mDateRangeSelectedListener.onDateSelect(start, end);
                    dismiss();
                } else {
                    ToastUtil.toastL(context, context.getString(R.string.select_no_more_5_days));
                }

                break;

            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    @Override
    public void show() {
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            super.show();
        }
    }

    private void selectedDates(ArrayList<Date> mDates) {
        calendarPickerView.setDecorators(Collections.<CalendarCellDecorator>emptyList());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.add(Calendar.MONTH, -calendar.get(Calendar.MONTH)); // 上一年的第一个月份开始
        // 上一年的第一个月的第一天， 日起选择器的第一天， 处于active状态。
        calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_MONTH) + 1);
        Date startDate = calendar.getTime();

        if (!isEndDateTag()) {
//            calendarPickerView.setDecorators(Arrays.<CalendarCellDecorator>asList(new HBAppCalendarCellDecorator(context)));
//            calendarPickerView.setCustomDayView(new CalendarCellDayViewAdapter());
//            calendarPickerView.setDecorators(Collections.<CalendarCellDecorator>emptyList());
            calendarPickerView.setCustomDayView(new DefaultDayViewAdapter());
            fluentInitializer = calendarPickerView.init(startDate, new Date());
            fluentInitializer.inMode(CalendarPickerView.SelectionMode.RANGE)
                    .withSelectedDates(mDates);
        }
    }

    public boolean isEndDateTag() {
        return isEndDateTag;
    }

    public void setEndDateTag(boolean endDateTag) {
        isEndDateTag = endDateTag;
    }

    public int getBetweenDays() {
        return betweenDays;
    }

    public void setBetweenDays(int betweenDays) {
        this.betweenDays = betweenDays;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }
}
