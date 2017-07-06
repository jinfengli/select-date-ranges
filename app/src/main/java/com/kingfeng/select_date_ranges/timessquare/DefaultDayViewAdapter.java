package com.kingfeng.select_date_ranges.timessquare;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingfeng.select_date_ranges.R;


public class DefaultDayViewAdapter implements DayViewAdapter {
    @Override
    public void makeCellView(CalendarCellView parent) {
//      TextView textView = new TextView(
//              new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_CalendarDate));
//      textView.setDuplicateParentStateEnabled(true);
//      parent.addView(textView);
//      parent.setDayOfMonthTextView(textView);

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_day_view, null);
        parent.addView(layout);
        parent.setDayOfMonthTextView((TextView) layout.findViewById(R.id.day_view));
        parent.setDayOfMonthCommentView((TextView) layout.findViewById(R.id.tv_calendar_cell_view_comment));
        parent.setLinearLayoutBg((LinearLayout) layout.findViewById(R.id.ll_custom));
    }
}
