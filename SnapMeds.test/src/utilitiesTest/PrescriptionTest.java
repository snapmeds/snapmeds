package utilitiesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.utilities.Dosage;
import com.utilities.Drug;
import com.utilities.Prescription;

public class PrescriptionTest {
	private static final Prescription prescription1 = new Prescription();
	private static final Prescription prescription2 = new Prescription();

	private static final Drug DRUG = new Drug();
	private static final Drug DIFFERENT_DRUG = new Drug();

	private static final Dosage DOSAGE = new Dosage();
	private static final Dosage DIFFERENT_DOSAGE = new Dosage();

	@BeforeClass
	public static void setup() {

		// Make drug and differentDrug distinguishable
		DRUG.setSetID("setid1");
		DIFFERENT_DRUG.setSetID("setid2");

		// Make dosage and differentDosage distinguishable
		DOSAGE.setInstructions("1");
		DIFFERENT_DOSAGE.setInstructions("2");
	}

	public static void populateFakePrescription(Prescription prescription) {
		prescription.setDrug(DRUG);
		prescription.setDosage(DOSAGE);
		prescription.setNote("note");
		prescription.setImagePath("imagepath");
	}

	@Before
	public void resetPrescriptions() {
		populateFakePrescription(prescription1);
		populateFakePrescription(prescription2);
	}

	@Test
	public void testEqualsWorks() {
		assertTrue(prescription1.equals(prescription1));
	}

	@Test
	public void testEqualsHandlesNull() {
		assertFalse(prescription1.equals(null));
	}

	@Test
	public void testEqualsHandlesNullDrug() {
		prescription1.setDrug(null);
		assertFalse(prescription1.equals(prescription2));
		assertFalse(prescription2.equals(prescription1));
		assertTrue(prescription1.equals(prescription1));
	}

	@Test
	public void testEqualsHandlesNullNote() {
		prescription1.setNote(null);
		assertFalse(prescription1.equals(prescription2));
		assertFalse(prescription2.equals(prescription1));
		assertTrue(prescription1.equals(prescription1));
	}

	@Test
	public void testEqualsHandlesNullDosage() {
		prescription1.setDosage(null);
		assertFalse(prescription1.equals(prescription2));
		assertFalse(prescription2.equals(prescription1));
		assertTrue(prescription1.equals(prescription1));
	}

	@Test
	public void testEqualsHandlesNullImagePath() {
		prescription1.setImagePath(null);
		assertFalse(prescription1.equals(prescription2));
		assertFalse(prescription2.equals(prescription1));
		assertTrue(prescription1.equals(prescription1));
	}

	@Test
	public void testEqualsChecksDrug() {
		prescription2.setDrug(DIFFERENT_DRUG);
		assertFalse(prescription1.equals(prescription2));
	}

	@Test
	public void testEqualsChecksNote() {
		prescription2.setNote("different");
		assertFalse(prescription1.equals(prescription2));
	}

	@Test
	public void testEqualsChecksDosage() {
		prescription2.setDosage(DIFFERENT_DOSAGE);
		assertFalse(prescription1.equals(prescription2));
	}

	@Test
	public void testEqualsChecksImagePath() {
		prescription2.setImagePath("different");
		assertFalse(prescription1.equals(prescription2));
	}

	@Test
	public void testSameDrugWorks() {
		assertTrue(prescription1.sameDrug(prescription2));
		assertTrue(prescription2.sameDrug(prescription1));
	}

	@Test
	public void testSameDrugHandlesNull() {
		assertFalse(prescription1.sameDrug(null));
	}

	@Test
	public void testSameDrugHandlesVacuousCase() {
		prescription1.setDrug(null);
		prescription2.setDrug(null);
		assertTrue(prescription1.sameDrug(prescription2));
	}

	@Test
	public void testSameDrugHandlesNullDrug() {
		prescription2.setDrug(null);
		assertFalse(prescription1.sameDrug(prescription2));
		assertFalse(prescription2.sameDrug(prescription1));
	}

	@Test
	public void testSameDrugChecksDrug() {
		prescription2.setDrug(DIFFERENT_DRUG);
		assertFalse(prescription1.sameDrug(prescription2));
	}
}
