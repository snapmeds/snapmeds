package com.snapmeds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dosage.CalendarReminder;
import com.dosage.RemindersActivity;
import com.dosage.NotificationReminder;
import com.utilities.Dosage;
import com.utilities.DosageParser;
import com.utilities.Frequency;
import com.utilities.FrequencyUnit;
import com.utilities.MedicineCabinet;
import com.utilities.Prescription;

/**
 * Activity that shows information about a prescribed drug a user has added to
 * their medicine cabinet.
 * 
 * @author Matt Omori
 * @author Terence Zhao
 * @author Bobby Heidkamp
 * @author Tom Zhang
 * @author Joel Dodge
 * @author Onur Karaman
 */
public class PrescriptionDetailActivity extends DetailBaseActivity {
	private static final int PRESCRIPTION_DETAIL_ACTIVITY_REQUEST_CODE = 1;
	private Prescription prescription;
	private Dosage dosage;
	private final FrequencyUnit[] freqUnits = { FrequencyUnit.HOURLY,
			FrequencyUnit.DAILY, FrequencyUnit.WEEKLY, FrequencyUnit.MONTHLY };
	private final FrequencyUnit[] durationUnits = { FrequencyUnit.INVALID,
			FrequencyUnit.HOURLY, FrequencyUnit.DAILY, FrequencyUnit.WEEKLY,
			FrequencyUnit.MONTHLY };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		setContentView(R.layout.activity_detail_prescription);

		AndroidStorage.setContext(this);
		loadPrescription();
		if (prescription != null) {
			drug = prescription.getDrug();
			dosage = prescription.getDosage();

			initializeNoteEdit();

			initializeImage();

			updateDosageInfo();

			initializeInformation();
		}

		// Set the dosage title
		TextView dosageTextTitle = ((TextView) findViewById(R.id.dosageTextTitle));
		dosageTextTitle.setTextAppearance(getApplicationContext(),
				R.style.detail_information_title);
		dosageTextTitle.setText(R.string.dosage_summary);

	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidStorage.setContext(this);
		loadPrescription();
		updateDosageInfo();
	}

	private void loadPrescription() {
		String setId = getIntent().getStringExtra(IntentProtocol.DRUG_SET_ID);
		prescription = MedicineCabinet.getInstance().get(setId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_prescription, menu);
		return true;
	}

	private void updateDosageInfo() {
		// Set dosage text if there is one
		Button dosageEdit = ((Button) findViewById(R.id.dosageEdit));
		if (dosage != null && !dosage.toString().isEmpty()) {
			dosageEdit.setText(R.string.dosage_edit);
			TextView dosageText = ((TextView) findViewById(R.id.dosageText));
			dosageText.setText(dosage.toString());
		} else {
			dosageEdit.setText(R.string.dosage_add);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dosage:
			Intent dosage_intent = new Intent(this, RemindersActivity.class);
			dosage_intent.putExtra(IntentProtocol.DRUG_SET_ID, prescription
					.getDrug().getSetID());
			startActivity(dosage_intent);
			return true;
		case R.id.remove_prescription:
			AlertDialog removeDialog = createRemoveDialog();
			removeDialog.show();
			return true;
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed
			// in the Action Bar.
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	/**
	 * showRemoveDialog creates the confirmation alert dialog for removing a
	 * drug. "Cancel" will cancel the dialog, "remove" will remove the
	 * prescription
	 * 
	 * @return AlertDialog for removing a prescription
	 */
	private AlertDialog createRemoveDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.detail_prescription_remove_dialog_title);
		builder.setMessage(R.string.detail_prescription_remove_dialog_message);
		builder.setIconAttribute(android.R.attr.alertDialogIcon);
		builder.setPositiveButton(R.string.remove,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						try {
							Dosage dosage = prescription.getDosage();
							if (dosage != null) {
								dosage.cancelReminders(PrescriptionDetailActivity.this);
							}
							MedicineCabinet.getInstance().remove(prescription);
							removePrescriptionImage();
						} catch (Exception e) {
							e.printStackTrace();
						}
						Intent goHome = new Intent(
								PrescriptionDetailActivity.this,
								MainActivity.class);
						goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
								| Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(goHome);
						finish();
					}
				});
		builder.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User cancelled the dialog
					}
				});
		return builder.create();

	}

	/**
	 * Initializes the note editing part for the PrescriptionDetailActivity
	 * page. Loads the prescription notes if it exists and defines the process
	 * of editing the prescription notes.
	 */
	protected void initializeNoteEdit() {
		String note = prescription.getNote() == null ? "" : prescription
				.getNote();
		EditText noteField = (EditText) findViewById(R.id.note);
		noteField.setEnabled(false);
		noteField.setText(note);
		final Button editNoteButton = (Button) findViewById(R.id.prescription_note_edit);
		updateEditNoteButton();

		editNoteButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText noteField = (EditText) findViewById(R.id.note);
				boolean isEnabled = noteField.isEnabled();
				if (isEnabled) {
					String note = noteField.getText().toString();
					updatePrescriptionNote(note);
					updateEditNoteButton();
				} else {
					editNoteButton
							.setText(R.string.detail_prescription_note_save);
					noteField.requestFocus();
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(noteField,
							InputMethodManager.SHOW_IMPLICIT);
				}

				noteField.setEnabled(!isEnabled);
			}

			/**
			 * Updates and saves Prescription note
			 * 
			 * @param note
			 *            - the note we wish to save to the Prescription
			 */
			private void updatePrescriptionNote(String note) {
				prescription.setNote(note);
				prescription.save();
			}
		});
	}

	/**
	 * Sets Edit Note button's text to be "add" or "edit" depending on whether a
	 * note exists already.
	 */
	private void updateEditNoteButton() {
		Button editNoteButton = (Button) findViewById(R.id.prescription_note_edit);
		if (prescription.getNote() == null || prescription.getNote().equals("")) {
			editNoteButton.setText(R.string.detail_prescription_note_add);
		} else {
			editNoteButton.setText(R.string.detail_prescription_note_edit);
		}
	}

	/**
	 * Initializes the image display for the PrescriptionDetailActivity page.
	 * Loads the prescription image if it exists and defines the process of
	 * triggering the MediaStore Capture Image.
	 */
	private void initializeImage() {

		LoaderImageView loaderImageView = (LoaderImageView) findViewById(R.id.image);
		// load prescription image if it exists
		String imagePath = prescription.getImagePath();
		if (imagePath != null) {
			loaderImageView.displayLoading();
			File basePath = getFilesDir();
			File imageFile = new File(basePath, imagePath);
			new ImageWorkerTask(loaderImageView).execute(imageFile
					.getAbsolutePath());
		} else {
			loaderImageView.displayDefaultImage();
		}

		// trigger the ImageDialogFragment window after clicking the ImageView
		final Activity prescriptionDetailActivity = this;
		loaderImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				DialogFragment imageDialogFragment = new ImageDialogFragment(
						prescriptionDetailActivity);
				imageDialogFragment.show(
						prescriptionDetailActivity.getFragmentManager(),
						"imageDialog");
			}
		});
	}

	/**
	 * Displays the returned bitmap from the MediaStore Capture Image Intent and
	 * updates the prescription object to hold the bitmap's file path.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PRESCRIPTION_DETAIL_ACTIVITY_REQUEST_CODE
				&& resultCode == Activity.RESULT_OK) {
			/*
			 * REFERENCE:
			 * http://dharmendra4android.blogspot.in/2012/04/save-captured
			 * -image-to-applications.html tells you how to extract the bitmap
			 * from the Extras
			 */
			Bitmap image = (Bitmap) data.getExtras().get("data");

			saveImage(image);
			LoaderImageView loaderImageView = (LoaderImageView) findViewById(R.id.image);
			File basePath = getFilesDir();
			File imageFile = new File(basePath, prescription.getImagePath());
			new ImageWorkerTask(loaderImageView).execute(imageFile
					.getAbsolutePath());

		}
	}

	/**
	 * Saves the given image and updates the prescription's image path to the
	 * location of the image
	 * 
	 * @param image
	 *            - the bitmap we wish to save
	 */
	private void saveImage(Bitmap image) {
		try {
			String filename = prescription.getDrug().getName() + ".jpg";
			FileOutputStream fos = openFileOutput(filename,
					Context.MODE_PRIVATE);
			int compressionQuality = 85;
			image.compress(Bitmap.CompressFormat.JPEG, compressionQuality, fos);
			fos.close();
			prescription.setImagePath(filename);
			prescription.save();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			prescription.setImagePath(null);
			prescription.save();
		} catch (IOException e) {
			e.printStackTrace();
			prescription.setImagePath(null);
			prescription.save();
		}
	}

	/**
	 * Removes the prescription image from the prescription object and deletes
	 * the image
	 */
	private void removePrescriptionImage() {
		String imagePath = prescription.getImagePath();
		if (imagePath != null) {
			deleteFile(imagePath);
			prescription.setImagePath(null);
			prescription.save();
		}
	}

	class ImageDialogFragment extends DialogFragment {

		private final String[] options = new String[] { "New Image",
				"Remove Image", "Cancel" };
		private final int newImageIndex = 0;
		private final int removeImageIndex = 1;
		private final int cancelImageIndex = 2;
		private Activity parentActivity;

		public ImageDialogFragment(Activity activity) {
			parentActivity = activity;
		}

		/*
		 * Take a picture for prescription on "New Image" click Remove the
		 * prescription's image in "Remove Image" click Do nothing on "Cancel"
		 * click REFERENCE:
		 * http://developer.android.com/guide/topics/ui/dialogs.html
		 */
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			builder.setItems(options, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (which == newImageIndex) {
						Intent cameraIntent = new Intent(
								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						parentActivity.startActivityForResult(cameraIntent,
								PRESCRIPTION_DETAIL_ACTIVITY_REQUEST_CODE);
					} else if (which == removeImageIndex) {
						removePrescriptionImage();
						LoaderImageView loaderImageView = (LoaderImageView) findViewById(R.id.image);
						loaderImageView.displayDefaultImage();
					} else if (which == cancelImageIndex) {
						// do nothing
					}
				}
			});

			return builder.create();
		}
	}

	// TODO pull this into a separate dosage util component?

	/**
	 * Open dialog for entering dosage text. User can choose to parse, skip, or
	 * cancel.
	 * 
	 * @param view
	 *            - edit dosage button
	 */
	public void onDosageEdit(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		final View dosageParseView = inflater.inflate(R.layout.dosage_parse,
				null);
		builder.setView(dosageParseView)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.dosage_enter)
				.setPositiveButton(R.string.confirm,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Dosage tempDosage = parseDosageText(dosageParseView);
								if (tempDosage == null) {
									Toast.makeText(getApplicationContext(),
											R.string.failed_parse,
											Toast.LENGTH_SHORT).show();
								}
								onSetDosage(tempDosage);
							}
						})
				.setNeutralButton(R.string.skip,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// This dosage param will be null if no previous
								// dosage
								// has been saved
								// otherwise, the user will edit the previously
								// saved
								// dosage.
								onSetDosage(dosage);
							}
						}).setNegativeButton(R.string.cancel, null).show();
	}

	/**
	 * Open dialog for setting dosage.
	 * 
	 * @param tempDosage
	 *            - dosage object from the auto parser. null if failed parse, or
	 *            if manual enter
	 */
	private void onSetDosage(Dosage tempDosage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		final View dosageFieldsView = inflater.inflate(R.layout.dosage_fields,
				null);
		builder.setView(dosageFieldsView)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton(R.string.save,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								if (dosage != null) {
									dosage.cancelReminders(getApplicationContext());
								}

								dosage = createDosageFromFields(dosageFieldsView);

								CalendarReminder calReminder = new CalendarReminder(
										prescription.getDrug().getName(),
										dosage.getQuantity());
								NotificationReminder notifyReminder = new NotificationReminder(
										drug.getSetID(), prescription.getDrug()
												.getName(), dosage
												.getQuantity());

								dosage.addReminder(calReminder);
								dosage.addReminder(notifyReminder);

								// Save new dosage
								prescription.setDosage(dosage);
								prescription.save();
								updateDosageInfo();
							}
						}).setNegativeButton(R.string.cancel, null);

		initializeSpinners(dosageFieldsView);

		if (tempDosage != null) {
			builder.setTitle(R.string.verify).show();
			setFieldsFromDosage(dosageFieldsView, tempDosage);
		} else {
			builder.setTitle(R.string.manual_enter).show();
		}

	}

	// quantity and number of times work... nothing else seems to be storing
	// properly
	private void setFieldsFromDosage(View dosageFieldsView, Dosage tempDosage) {
		Spinner numTimeSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.numTimes);
		Spinner intervalSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.interval);
		Spinner unitSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.unit);
		Spinner durationNumSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.durationNum);
		Spinner durationUnitSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.durationUnit);
		EditText quantityView = (EditText) dosageFieldsView
				.findViewById(R.id.dosageQuantity);
		EditText otherInfoView = (EditText) dosageFieldsView
				.findViewById(R.id.otherInfo);
		String quantity = tempDosage.getQuantity();
		Frequency freq = tempDosage.getFrequency();
		int numTimes = freq.getNumTimes();
		int interval = freq.getInterval();
		FrequencyUnit unit = freq.getUnit();
		int duration = freq.getDuration();
		FrequencyUnit durationUnit = freq.getDurationUnit();
		String otherInfo = tempDosage.getOtherInfo();
		quantityView.setText(quantity);
		if (numTimes < 9) {
			// Subtract 1 because there is no empty selection for numTimes
			numTimeSpinner.setSelection(numTimes - 1);
		}
		if (interval < 9) {
			// Subtract 1 because the empty selection replaces the 1 selection
			// for interval
			intervalSpinner.setSelection(interval - 1);
		}
		unitSpinner.setSelection(Arrays.asList(freqUnits).indexOf(unit));
		if (duration < 9) {
			durationNumSpinner.setSelection(duration);
		}
		durationUnitSpinner.setSelection(Arrays.asList(durationUnits).indexOf(
				durationUnit));
		otherInfoView.setText(otherInfo);
	}

	private Dosage parseDosageText(View dosageParseView) {
		EditText dosageInputText = ((EditText) dosageParseView
				.findViewById(R.id.dosageInputText));
		String dosageText = dosageInputText.getText().toString();
		dosageInputText.setText("");
		dosageInputText.clearFocus();
		return DosageParser.parseDosageString(dosageText);
	}

	private void initializeSpinners(View dosageFieldsView) {
		initializeSpinner(dosageFieldsView, R.id.numTimes, R.array.num_times);
		initializeSpinner(dosageFieldsView, R.id.interval, R.array.interval);
		initializeSpinner(dosageFieldsView, R.id.unit, R.array.unit);
		initializeSpinner(dosageFieldsView, R.id.durationNum,
				R.array.durationNum);
		initializeSpinner(dosageFieldsView, R.id.durationUnit,
				R.array.durationUnit);
	}

	private void initializeSpinner(View dosageFieldsView, int spinnerId,
			int arrayId) {
		Spinner spinner = (Spinner) dosageFieldsView.findViewById(spinnerId);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, arrayId, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}

	/**
	 * createDosageFromFields creates a dosage object from the spinners and text
	 * fields
	 * 
	 * @param dosageFieldsView
	 *            The view with all the data fields
	 * @return dosage object holding the field data
	 */
	private Dosage createDosageFromFields(View dosageFieldsView) {
		// Base new dosage on previously saved dosage
		Dosage newDosage = new Dosage(dosage);
		Spinner numTimeSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.numTimes);
		Spinner intervalSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.interval);
		Spinner unitSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.unit);
		Spinner durationNumSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.durationNum);
		Spinner durationUnitSpinner = (Spinner) dosageFieldsView
				.findViewById(R.id.durationUnit);
		EditText quantityView = (EditText) dosageFieldsView
				.findViewById(R.id.dosageQuantity);
		EditText otherInfoView = (EditText) dosageFieldsView
				.findViewById(R.id.otherInfo);

		// Add 1 because there is no empty selection for numTimes
		int numTimes = numTimeSpinner.getSelectedItemPosition() + 1;
		// Add 1 because the empty selection replaces the 1 selection for
		// interval
		int interval = intervalSpinner.getSelectedItemPosition() + 1;
		FrequencyUnit freqUnit = freqUnits[unitSpinner
				.getSelectedItemPosition()];
		int durationNum = durationNumSpinner.getSelectedItemPosition();
		FrequencyUnit durationUnit = durationUnits[durationUnitSpinner
				.getSelectedItemPosition()];
		String quantity = quantityView.getText().toString();
		Frequency freq = new Frequency(numTimes, freqUnit, interval);
		freq.setDuration(durationNum);
		freq.setDurationUnit(durationUnit);
		String otherInfo = otherInfoView.getText().toString();

		newDosage.setQuantity(quantity);
		newDosage.setFrequency(freq);
		newDosage.setOtherInfo(otherInfo);
		return newDosage;
	}
}
