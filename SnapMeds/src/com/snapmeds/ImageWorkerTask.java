package com.snapmeds;

import java.io.File;
import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/**
 * ImageWorkerTask executes the loading of an image in the background. It is an
 * AsyncTask Based off:
 * https://developer.android.com/training/displaying-bitmaps/process-bitmap.html
 * 
 * @author Bobby Heidkamp
 * 
 */
public class ImageWorkerTask extends AsyncTask<String, Void, Bitmap> {
	private final WeakReference<LoaderImageView> loaderImageViewReference;
	private String imagePath = "";

	public ImageWorkerTask(LoaderImageView loaderImageView) {
		// weak reference ensures that it can be garbage collected
		loaderImageViewReference = new WeakReference<LoaderImageView>(
				loaderImageView);
	}

	// Decode image in background
	@Override
	protected Bitmap doInBackground(String... params) {
		imagePath = params[0];
		File imageFile = new File(imagePath);
		Bitmap thumbnail = getBitmapFromFile(imageFile);
		return thumbnail;
	}

	// Once complete, check if ImageView is still around and set bitmap
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (loaderImageViewReference != null) {
			final LoaderImageView loaderImageView = loaderImageViewReference
					.get();
			if (loaderImageView != null) {
				if (bitmap != null) {
					loaderImageView.displayImage(bitmap);
				} else {
					loaderImageView.displayDefaultImage();
				}
			}
		}
	}

	/**
	 * Gets the Bitmap image from the given imageFile
	 * 
	 * @param imageFile
	 *            - the File containing the Bitmap
	 * @return Bitmap - representing the bitmap found at imageFile, returns null
	 *         on error
	 */
	private Bitmap getBitmapFromFile(File imageFile) {
		if (imageFile.exists()) {
			Bitmap photo = BitmapFactory
					.decodeFile(imageFile.getAbsolutePath());
			Bitmap thumbnail = Bitmap.createScaledBitmap(photo, 100, 100, true);
			return thumbnail;
		} else {
			return null;
		}
	}
}
