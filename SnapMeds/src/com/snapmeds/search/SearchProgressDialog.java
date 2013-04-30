package com.snapmeds.search;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

/**
 * Basic progress dialog modification. Makes sure that on the back button
 * pressed the dialog cancels It also cancels the corresponding async task
 * 
 * @author bheidkamp3
 * 
 */
public class SearchProgressDialog extends ProgressDialog {

	@SuppressWarnings("rawtypes")
	private AsyncTask asyncTask;
	private Context context;

	@SuppressWarnings("rawtypes")
	public SearchProgressDialog(Context context, AsyncTask async) {
		super(context);
		this.context = context;
		asyncTask = async;
	}

	@Override
	public void onBackPressed() {
		this.cancel();
		asyncTask.cancel(true);

	}

	@Override
	public void show() {
		super.show();
		timerDelayRemoveDialog(10000, this);

	}

	private void timerDelayRemoveDialog(long time, final Dialog d) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				if (d.isShowing()) {
					d.cancel();
					asyncTask.cancel(true);
					Toast.makeText(context,
							"Search Timed Out, Try Again Later",
							Toast.LENGTH_LONG).show();
				}
			}
		}, time);
	}

}
