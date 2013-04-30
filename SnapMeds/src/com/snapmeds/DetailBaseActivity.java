package com.snapmeds;

import android.app.Activity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.utilities.Drug;

/**
 * Basic Activity that displays information about a drug. Used by Medication
 * Detail and Prescription Detail views.
 * 
 * @author Terence Zhao
 * @author Matt Omori
 * @author Bobby Heidkamp
 * @author Caleb Johnson
 * 
 */
public class DetailBaseActivity extends Activity {
	protected Drug drug;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	/**
	 * Creates expandable view sections with titles for every member of the Drug
	 * class.
	 * 
	 * @see Drug
	 */
	protected void initializeInformation() {
		((TextView) findViewById(R.id.name)).setText(drug.getName());
		addInformation(R.string.drug_generic_name, drug.getGenericNames());
		addInformation(R.string.drug_uses, drug.getUses());
		addInformation(R.string.drug_warnings, drug.getWarnings());
		addInformation(R.string.drug_precautions, drug.getPrecautions());
		addInformation(R.string.drug_adverse_reactions,
				drug.getAdverseReactions());
		addInformation(R.string.drug_box_warnings, drug.getBoxWarnings());
		addInformation(R.string.drug_conflicting_conditions,
				drug.getConflictingConditions());
		addInformation(R.string.drug_medication_guide,
				drug.getMedicationGuide());

	}

	/**
	 * Creates a view section with a title.
	 * 
	 * @param titleStringId
	 *            the String resource ID of the section title
	 * @param information
	 *            the String used to populate the section information
	 */
	protected void addInformation(int titleStringId, String information) {
		String title = getString(titleStringId);

		TextView titleView = buildView("- " + title,
				R.style.detail_information_title);
		TextView informationView = buildView(information,
				R.style.detail_information_content);

		titleView.setTag(title);
		informationView.setTag(titleView);

		titleView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				toggleInformation(v);
			}
		});
	}

	/**
	 * Creates and adds a TextView to the information container.
	 * 
	 * @param text
	 *            the text to populate the new TextView
	 * @param styleID
	 *            the style resource ID to give to the new TextView
	 * @return the resulting TextView
	 */
	private TextView buildView(String text, int styleID) {
		TextView newView = new TextView(getApplicationContext());
		newView.setText(text);
		newView.setTextAppearance(getApplicationContext(), styleID);
		((LinearLayout) findViewById(R.id.detail_information_container))
				.addView(newView);
		return newView;
	}

	/**
	 * Expands or hides an information section after its title has been clicked.
	 * Used by the onClickListeners for title TextViews.
	 * 
	 * @param v
	 *            the TextView representing the title of the section to be
	 *            toggled.
	 */
	private void toggleInformation(View v) {
		TextView titleView = (TextView) v;
		TextView informationView = (TextView) v.getRootView().findViewWithTag(
				titleView);

		String toggleChar = "";
		if (informationView.getVisibility() == View.GONE) {
			informationView.setVisibility(View.VISIBLE);
			toggleChar = "- ";
		} else {
			informationView.setVisibility(View.GONE);
			toggleChar = "+ ";
		}
		titleView.setText(toggleChar + titleView.getTag());
	}

}
