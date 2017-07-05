package com.kingfeng.select_date_ranges.timessquare;

/**
 * TODO:
 *
 * @author lijf
 * @version 1.0.0
 * @date 2017-07-02 16:54:01
 * @copyright (c) wonhigh.cn All rights reserved.
 */
public class HBAppCalendarCellDecorator implements CalendarCellDecorator {

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
    }
}
