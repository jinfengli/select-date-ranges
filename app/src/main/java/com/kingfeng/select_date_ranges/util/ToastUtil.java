
package com.kingfeng.select_date_ranges.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

	private static Toast mToast;
	private static final int X_OFFSET = 0;
	private static final int Y_OFFSET = 50;

	public static void toastL(Context context, int resId) {
		//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();        
		//        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		//		toast.setGravity(Gravity.TOP, 0, 50);
		//		toast.show();
		toast(context, resId, Toast.LENGTH_LONG);

	}

	/**
	 * 显示吐司信息（较长时间）
	 * 
	 * @param context
	 * @param message
	 */
	public static void toastL(Context context, String message) {
		//        Toast.makeText(context, text, Toast.LENGTH_LONG).show();        
		//        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		//		toast.setGravity(Gravity.TOP, 0, 50);
		//		toast.show();
		toast(context, message, Toast.LENGTH_LONG);
	}

	/**
	 * 显示吐司信息（较短时间）
	 * 
	 * @param context
	 * @param message
	 */
	public static void toasts(Context context, String message) {
		//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();        
		//        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		//		toast.setGravity(Gravity.TOP, 0, 50);
		//		toast.show();
		toast(context, message, Toast.LENGTH_SHORT);
	}

	/**
	 * @方法名: toasts
	 * @描述: TODO(短时间吐司)
	 * @设定: @param context
	 * @设定: @param message 吐司内容，为string里面的内容id
	 * @返回: void 返回类型
	 * @日期: 2014-5-26 上午9:55:09
	 * @throws
	 */
	public static void toasts(Context context, int resId) {
		//      Toast.makeText(context, message, Toast.LENGTH_SHORT).show();        
		//      Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		//		toast.setGravity(Gravity.TOP, 0, 50);
		//		toast.show();
		toast(context, resId, Toast.LENGTH_SHORT);
	}

	/**
	 * 显示吐司信息交给handler处理（较长时间）
	 * 
	 * @param context
	 * @param message
	 * @param handler
	 */
	public static void toastLInThread(final Context context, final String message, Handler handler) {

		handler.post(new Runnable() {
			@Override
			public void run() {
				//                ToastUtil.toastL(context, message);
				ToastUtil.toast(context, message, Toast.LENGTH_LONG);
			}
		});
	}

	/**
	 * 显示吐司信息交给handler处理（较短时间）
	 * 
	 * @param context
	 * @param message
	 * @param handler
	 */
	public static void toastsInThread(final Context context, final String message, Handler handler) {

		handler.post(new Runnable() {

			@Override
			public void run() {
				//                ToastUtil.toasts(context, text);
				ToastUtil.toast(context, message, Toast.LENGTH_SHORT);
			}
		});
	}

	/***
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */

	public static void toast(Context context, String message, int duration) {
	
		/**mToast的复用*/
		if (mToast == null) {
			mToast = Toast.makeText(context, message, duration);
		} else {
			mToast.setText(message);
			mToast.setDuration(duration);
		}
		mToast.setGravity(Gravity.CENTER, X_OFFSET, Y_OFFSET);
		mToast.show();

	}

	/***
	 * 
	 * @param context
	 * @param resId
	 * @param duration
	 */

	public static void toast(Context context, int resId, int duration) {
		/**mToast的复用*/
		if (mToast == null) {
			mToast = Toast.makeText(context, resId, duration);
		} else {
			mToast.setText(resId);
			mToast.setDuration(duration);
		}
		mToast.setGravity(Gravity.TOP, X_OFFSET, Y_OFFSET);
		mToast.show();
	}
	/***
	 * 关闭Toast
	 */
	public static void cancelToast() {
		if (mToast != null) {
			mToast.cancel();			
		}
	}
	

	
	

}
