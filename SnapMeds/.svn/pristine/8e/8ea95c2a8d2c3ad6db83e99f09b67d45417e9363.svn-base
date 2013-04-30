package com.snapmeds.search.manual;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.snapmeds.IntentProtocol;
import com.snapmeds.R;
import com.utilities.Drug;

/**
 * DrugAdapter class for displaying drugs in a listview Extends arrayadapter and
 * sets how drugs should be displayed when in a listview based off of
 * https://devtut
 * .wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/
 * 
 * @author bheidkamp3
 * 
 */
public class DrugAdapter extends ArrayAdapter<Drug> {
	private ArrayList<Drug> drugs;
	private Context context;
	private Activity activity;

	/**
	 * Constructor for a drug adapter
	 * 
	 * @param context
	 *            : the application/activity context for this adapter
	 * @param drugs
	 *            : ArrayList of drugs for the adapter
	 */
	public DrugAdapter(Context context, Activity a, ArrayList<Drug> drugs) {
		super(context, R.layout.drug_item_row, drugs);
		this.context = context;
		this.drugs = drugs;
		this.activity = a;
	}

	/**
	 * getView defines how each drug will look
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.drug_item_row, parent, false);
		TextView drugNameView = (TextView) view.findViewById(R.id.label);
		Drug currDrug = drugs.get(position);
		if (view != null && currDrug != null) {
			drugNameView.setText(currDrug.getName());
			final Drug drug = drugs.get(position);
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent drugDetailIntent = new Intent(getContext(),
							com.snapmeds.DrugDetailActivity.class);
					drugDetailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					drugDetailIntent.putExtra(IntentProtocol.DRUG_NAME,
							drug.getName());
					drugDetailIntent.putExtra(IntentProtocol.DRUG_GENERIC_NAME,
							drug.getGenericNames());
					drugDetailIntent.putExtra(IntentProtocol.DRUG_USES,
							drug.getUses());
					drugDetailIntent.putExtra(IntentProtocol.DRUG_WARNINGS,
							drug.getWarnings());
					drugDetailIntent.putExtra(IntentProtocol.DRUG_PRECAUTIONS,
							drug.getPrecautions());
					drugDetailIntent.putExtra(IntentProtocol.DRUG_BOX_WARNINGS,
							drug.getBoxWarnings());
					drugDetailIntent.putExtra(
							IntentProtocol.DRUG_CONFLICTING_CONDITIONS,
							drug.getConflictingConditions());
					drugDetailIntent.putExtra(
							IntentProtocol.DRUG_MEDICATION_GUIDE,
							drug.getMedicationGuide());
					drugDetailIntent.putExtra(IntentProtocol.DRUG_SET_ID,
							drug.getSetID());
					drugDetailIntent.putExtra(
							IntentProtocol.DRUG_ADVERSE_REACTIONS,
							drug.getAdverseReactions());
					activity.startActivity(drugDetailIntent);
				}

			});
		}
		// the view must be returned to our activity

		return view;
	}
}
