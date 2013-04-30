package com.snapmeds.search.scanner;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.query.DailyMedQueryer;
import com.snapmeds.IntentProtocol;
import com.snapmeds.search.SearchProgressDialog;
import com.utilities.Drug;

/**
 * Asynchronous task for retrieving drugs. This task was created to avoid
 * querying web in the main thread Offloads querying to another thread.
 * 
 * @author bheidkamp3
 * 
 */
public class RetrieveDrugsTask extends AsyncTask<String, Void, List<Drug>> {

	/**
	 * 
	 */
	private Activity scannerActivity;

	/**
	 * @param scannerActivity
	 */
	public RetrieveDrugsTask(Activity scannerActivity) {
		this.scannerActivity = scannerActivity;
	}

	SearchProgressDialog spinner;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		spinner = new SearchProgressDialog(scannerActivity, this);
		spinner.setMessage("Loading");
		spinner.setIndeterminate(true);
		spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		spinner.setCancelable(false);
		spinner.show();
	}

	@Override
	protected List<Drug> doInBackground(String... upcs) {

		String upc = upcs[0];
		DailyMedQueryer queryer = new DailyMedQueryer();
		List<Drug> drugs = null;
		// if upc is 12 in length then it contains padding,
		// otherwise it is the ndc
		if (upc.length() == 12) {
			String stripped = upc.substring(1, 11);
			drugs = queryer.getDrugsFromNDC(stripped);
		} else {
			drugs = queryer.getDrugsFromNDC(upc);
		}
		return drugs;

	}

	protected void onPostExecute(List<Drug> drugs) {
		if (drugs != null && !drugs.isEmpty()) {

			Drug drug = drugs.get(0);
			Intent searchIntent = new Intent(
					scannerActivity.getApplicationContext(),
					com.snapmeds.DrugDetailActivity.class);
			searchIntent.putExtra(IntentProtocol.DRUG_NAME, drug.getName());
			searchIntent.putExtra(IntentProtocol.DRUG_GENERIC_NAME,
					drug.getGenericNames());
			searchIntent.putExtra(IntentProtocol.DRUG_USES, drug.getUses());
			searchIntent.putExtra(IntentProtocol.DRUG_WARNINGS,
					drug.getWarnings());
			searchIntent.putExtra(IntentProtocol.DRUG_PRECAUTIONS,
					drug.getPrecautions());
			searchIntent.putExtra(IntentProtocol.DRUG_BOX_WARNINGS,
					drug.getBoxWarnings());
			searchIntent.putExtra(IntentProtocol.DRUG_CONFLICTING_CONDITIONS,
					drug.getConflictingConditions());
			searchIntent.putExtra(IntentProtocol.DRUG_MEDICATION_GUIDE,
					drug.getMedicationGuide());
			searchIntent.putExtra(IntentProtocol.DRUG_SET_ID, drug.getSetID());
			spinner.setIndeterminate(false);
			spinner.dismiss();
			scannerActivity.startActivity(searchIntent);
		} else if (isCancelled()) {
			spinner.setIndeterminate(false);
			spinner.dismiss();
		} else {
			spinner.setIndeterminate(false);
			spinner.dismiss();
			Toast.makeText(scannerActivity.getApplicationContext(),
					"Drug not found by barcode. Try a manual search",
					Toast.LENGTH_LONG).show();
		}
	}
}