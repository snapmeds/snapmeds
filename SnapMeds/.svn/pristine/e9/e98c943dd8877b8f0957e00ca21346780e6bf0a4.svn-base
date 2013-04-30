package com.snapmeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.utilities.Drug;
import com.utilities.MedicineCabinet;
import com.utilities.Prescription;

/**
 * Activity that shows drug information based on search results.
 * 
 * @author Terence Zhao
 * @author Matt Omori
 * @author Bobby Heidkamp
 * @author Joel Dodge
 * 
 */
public class DrugDetailActivity extends DetailBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_in);
		setContentView(R.layout.activity_detail_drug);
		Intent intent = getIntent();

		// Set up Drug Object
		drug = new Drug();

		drug.setSetID(intent.getStringExtra(IntentProtocol.DRUG_SET_ID));
		drug.setName(intent.getStringExtra(IntentProtocol.DRUG_NAME));
		drug.setGenericNames(intent
				.getStringExtra(IntentProtocol.DRUG_GENERIC_NAME));
		drug.setUses(intent.getStringExtra(IntentProtocol.DRUG_USES));
		drug.setWarnings(intent.getStringExtra(IntentProtocol.DRUG_WARNINGS));
		drug.setPrecautions(intent
				.getStringExtra(IntentProtocol.DRUG_PRECAUTIONS));
		drug.setAdverseReactions(intent
				.getStringExtra(IntentProtocol.DRUG_ADVERSE_REACTIONS));
		drug.setBoxWarnings(intent
				.getStringExtra(IntentProtocol.DRUG_BOX_WARNINGS));
		drug.setConflictingConditions(intent
				.getStringExtra(IntentProtocol.DRUG_CONFLICTING_CONDITIONS));
		drug.setMedicationGuide(intent
				.getStringExtra(IntentProtocol.DRUG_MEDICATION_GUIDE));

		initializeInformation();
	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidStorage.setContext(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_drug, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	// Changes the menu action button to reflect add medication or view
	// prescription
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_medication:
			Prescription newPrescription = new Prescription();
			newPrescription.setDrug(drug);
			try {
				MedicineCabinet.getInstance().add(newPrescription);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.invalidateOptionsMenu();
			Intent goToPrescription = new Intent(this,
					PrescriptionDetailActivity.class);
			goToPrescription.putExtra(IntentProtocol.DRUG_SET_ID,
					drug.getSetID());
			Toast.makeText(this,
					"Added " + drug.getName() + " to the Medicine Cabinet",
					Toast.LENGTH_LONG).show();
			startActivity(goToPrescription);
			return true;
		case R.id.view_prescription:
			Intent viewPrescription = new Intent(this,
					com.snapmeds.PrescriptionDetailActivity.class);
			viewPrescription.putExtra(IntentProtocol.DRUG_SET_ID,
					drug.getSetID());
			startActivity(viewPrescription);
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
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean inCabinet = MedicineCabinet.getInstance().inCabinet(
				drug.getSetID());
		MenuItem addMedication = menu.findItem(R.id.add_medication);
		addMedication.setVisible(!inCabinet);
		MenuItem viewPrescription = menu.findItem(R.id.view_prescription);
		viewPrescription.setVisible(inCabinet);
		return true;
	}
}