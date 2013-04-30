package utilitiesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.utilities.Drug;

public class DrugTest {
	private static final Drug drug1 = new Drug();
	private static final Drug drug2 = new Drug();

	private void populateFakeDrug(Drug drug) {
		drug.setAdverseReactions("adversereactions");
		drug.setBoxWarnings("boxwarnings");
		drug.setConflictingConditions("conflictingconditions");
		drug.setGenericNames("genericnames");
		drug.setMedicationGuide("medicationguide");
		drug.setName("name");
		drug.setPrecautions("precautions");
		drug.setSetID("setid");
		drug.setUses("uses");
		drug.setWarnings("warnings");
	}

	@Before
	public void resetDrugs() {
		populateFakeDrug(drug1);
		populateFakeDrug(drug2);
	}
	
	@Test
	public void testIsPopulatedWorks() {
		assertTrue(drug1.isPopulated());
	}
	
	@Test
	public void testIsPopulatedChecksNull() {
		drug1.setName(null);
		assertFalse(drug1.isPopulated());
	}
	
	@Test public void testIsPopulatedChecksString() {
		drug1.setName("");
		assertFalse(drug1.isPopulated());
		drug1.setName("false");
		assertFalse(drug1.isPopulated());
		drug1.setName("null");
		assertFalse(drug1.isPopulated());
	}

	@Test
	public void testEqualsWorks() {
		assertTrue(drug1.equals(drug2));
	}
	
	@Test
	public void testEqualsHandlesNull() {
		assertFalse(drug1.equals(null));
	}
	
	@Test
	public void testEqualsHandlesNullStrings() {
		drug1.setName(null);
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksAdverseReactions() {
		drug2.setAdverseReactions("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksBoxWarnings() {
		drug2.setBoxWarnings("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksConflictingConditions() {
		drug2.setConflictingConditions("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksGenericNames() {
		drug2.setGenericNames("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksMedicationGuide() {
		drug2.setMedicationGuide("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksName() {
		drug2.setName("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksPrecautions() {
		drug2.setPrecautions("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksSetid() {
		drug2.setSetID("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksUses() {
		drug2.setUses("different");
		assertFalse(drug1.equals(drug2));
	}

	@Test
	public void testEqualsChecksWarnings() {
		drug2.setWarnings("different");
		assertFalse(drug1.equals(drug2));
	}

}
