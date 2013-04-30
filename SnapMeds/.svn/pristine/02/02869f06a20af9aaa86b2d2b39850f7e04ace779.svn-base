package com.snapmeds;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * LoaderImageView supports displaying a progress indicator while loading an
 * image Once the image is loaded the progress indicator will be replaced with
 * the photo
 * 
 * @author Bobby Heidkamp
 * 
 */
public class LoaderImageView extends RelativeLayout {
	private Context context;
	private ProgressBar progressBar;
	private ImageView imageView;

	/**
	 * default constructor. Creates a progress bar as visible and an imageView
	 * as invisible
	 * 
	 * @param context
	 * @param attrSet
	 */
	public LoaderImageView(final Context context, final AttributeSet attrSet) {
		super(context, attrSet);
		instantiate(context, attrSet);
		progressBar.setVisibility(VISIBLE);
		imageView.setVisibility(INVISIBLE);
	}

	/**
	 * hides the image and displays loading indicator
	 */
	public void displayLoading() {
		progressBar.setVisibility(VISIBLE);
		imageView.setVisibility(INVISIBLE);

	}

	/**
	 * hides loading indicator and displays image
	 * 
	 * @param bitmap
	 *            image to display
	 */
	public void displayImage(Bitmap bitmap) {
		imageView.setImageBitmap(bitmap);
		progressBar.setVisibility(INVISIBLE);
		imageView.setVisibility(VISIBLE);
	}

	/**
	 * hides loading indicator and displays default image
	 */
	public void displayDefaultImage() {
		imageView.setImageResource(R.drawable.camera);
		progressBar.setVisibility(INVISIBLE);
		imageView.setVisibility(VISIBLE);
	}

	/**
	 * sets up and adds the imageView and progressBar
	 * 
	 * @param _context
	 * @param attrSet
	 */
	private void instantiate(final Context _context, AttributeSet attrSet) {
		context = _context;
		imageView = new ImageView(context, attrSet);
		imageView.setId(R.id.loader_image_imave_view);
		progressBar = new ProgressBar(context, attrSet);
		progressBar.setId(R.id.loader_image_view_progress);
		progressBar.setIndeterminate(true);

		addView(progressBar);
		addView(imageView);

		this.setGravity(Gravity.CENTER);
	}

}