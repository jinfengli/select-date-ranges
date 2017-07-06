package com.kingfeng.select_date_ranges.timessquare;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;

import com.kingfeng.select_date_ranges.R;

/**
 * TODO:
 *
 * @author lijf
 * @version 1.0.0
 * @date 2017-07-02 16:54:01
 * @copyright (c) wonhigh.cn All rights reserved.
 */
public class HBAppCalendarCellDecorator implements CalendarCellDecorator {

    private Context context;
    public HBAppCalendarCellDecorator(Context context) {
        this.context = context;
    }

    @Override
    public void decorate(CalendarCellView cellView, MonthCellDescriptor cell) {
//        String dateString = Integer.toString(date.getDate());
//        SpannableString string = new SpannableString(dateString + "");
//        string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
//                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        cellView.getDayOfMonthTextView().setText(string);

//        cellView.getDayOfMonthCommentView().setText("开始" + "\n" + "结束");

//        String cellDate = cell.getDataStr();
//        if (!cellView.getDayOfMonthTextView().equals(cellDate)) {
//            //设置文字
//            if (cell.getRangeState() == RangeState.FIRST) {
//                if (cellDate.contains("开始") && cellDate.contains("结束")) {
//                    cellView.getDayOfMonthTextView().setText(cellDate.substring(0,cellDate.indexOf("开始")));
//                    cellView.getDayOfMonthCommentView().setText("开始"+ "\n" + "结束");
//                } else if(cellDate.contains("开始")) {
//                    cellView.getDayOfMonthTextView().setText(cellDate.substring(0,cellDate.indexOf("开始")));
//                    cellView.getDayOfMonthCommentView().setText("开始");
//                }
//
//            } else if (cell.getRangeState() == RangeState.LAST) {
//                cellView.getDayOfMonthTextView().setText(cellDate.substring(0,cellDate.length() -2));
//                cellView.getDayOfMonthCommentView().setText("结束");
//            } else {
//                cellView.getDayOfMonthTextView().setText(cellDate);
//                cellView.getDayOfMonthCommentView().setText("");
//            }
//        }


//        if (!cellView.getDayOfMonthTextView().getText().equals(cellDate)) {
//            //设置文字
//            if (cell.getRangeState() == RangeState.FIRST) {
//                SpannableString spannableString = new SpannableString(cellDate);
//                AbsoluteSizeSpan span2 = new AbsoluteSizeSpan(20);
//                if (spannableString.toString().contains("开始") && spannableString.toString().contains("结束")) {
//                    // 开始，结束 ，另外还包含一个换行  共5个字符。
//                    spannableString.setSpan(span2, cellDate.length() - 5, cellDate.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    cellView.getDayOfMonthTextView().setText(spannableString);
////                    cellView.getDayOfMonthCommentView().setText("开始" + "\n" + "结束");
//                    cellView.getDayOfMonthTextView().setBackground(context.getResources().getDrawable(R.drawable.text_select_shape));
//
//                } else {
//                    spannableString.setSpan(span2, cellDate.length() - 2, cellDate.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    cellView.getDayOfMonthTextView().setText(spannableString);
////                    cellView.getDayOfMonthCommentView().setText("开始");
//                    cellView.getDayOfMonthTextView().setBackground(context.getResources().getDrawable(R.drawable.text_select_shape));
//                }
//            } else if (cell.getRangeState() == RangeState.LAST) {
//                SpannableString spannableString = new SpannableString(cellDate);
//                AbsoluteSizeSpan span2 = new AbsoluteSizeSpan(20);
//                spannableString.setSpan(span2, cellDate.length() - 2, cellDate.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                cellView.getDayOfMonthTextView().setText(spannableString);
//                cellView.getDayOfMonthCommentView().setText("结束");
//                cellView.getDayOfMonthTextView().setBackground(context.getResources().getDrawable(R.drawable.text_select_shape));
//
//            } else if(cell.getRangeState() == RangeState.MIDDLE) {
//                cellView.getDayOfMonthTextView().setText(cellDate);
//                cellView.getDayOfMonthCommentView().setText("");
//                cellView.getLinearLayoutBg().setBackgroundColor(context.getResources().getColor(R.color.calendar_selected_radius_bg));
//            } else {
//                cellView.getDayOfMonthTextView().setText(cellDate);
//                cellView.getDayOfMonthCommentView().setText("");
//            }
//        }

        String cellDate = cell.getDataStr();
//                    String cellDate = numberFormatter.format(cell.getValue());
        if (!cellView.getDayOfMonthTextView().equals(cellDate)) {
            // 设置文字
            if (cell.getRangeState() == RangeState.FIRST) {
                SpannableString spannableString = new SpannableString(cellDate);
                AbsoluteSizeSpan span2 = new AbsoluteSizeSpan(20);
                if (spannableString.toString().contains("开始") && spannableString.toString().contains("结束")) {
                    // 开始，结束 ，另外还包含一个换行  共5个字符。
//                    spannableString.setSpan(span2, cellDate.length() - 5, cellDate.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    cellView.getDayOfMonthTextView().setText(spannableString);
                    cellView.getDayOfMonthTextView().setText(cellDate.subSequence(0, cellDate.length() - 5));
                    cellView.getDayOfMonthCommentView().setText("开始" + "\n" + "结束");
                    cellView.getDayOfMonthTextView().setBackground(context.getResources().getDrawable(R.drawable.text_select_shape));

                } else {
//                    spannableString.setSpan(span2, cellDate.length() - 2, cellDate.length(),
//                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    cellView.getDayOfMonthTextView().setText(cellDate.subSequence(0, cellDate.length() - 2));
                    cellView.getDayOfMonthCommentView().setText("开始");
                    cellView.getDayOfMonthTextView().setBackground(context.getResources().getDrawable(R.drawable.text_select_shape));

                }
            } else if (cell.getRangeState() == RangeState.LAST) {
//                SpannableString spannableString = new SpannableString(cellDate);
//                AbsoluteSizeSpan span2 = new AbsoluteSizeSpan(20);
//                spannableString.setSpan(span2, cellDate.length() - 2, cellDate.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                cellView.getDayOfMonthTextView().setText(cellDate.subSequence(0, cellDate.length() - 2));
                cellView.getDayOfMonthTextView().setBackground(context.getResources().getDrawable(R.drawable.text_select_shape));
                cellView.getDayOfMonthCommentView().setText("结束");

            } else if(cell.getRangeState() == RangeState.MIDDLE) {
                cellView.getDayOfMonthTextView().setText(cellDate);
                cellView.getLinearLayoutBg().setBackgroundColor(context.getResources().getColor(R.color.calendar_selected_radius_bg));
            } else {
                cellView.getDayOfMonthTextView().setText(cellDate);
//             cellView.getLinearLayoutBg().setBackgroundColor(getResources().getColor(R.color.calendar_selected_radius_bg));
            }
        }


    }
}
