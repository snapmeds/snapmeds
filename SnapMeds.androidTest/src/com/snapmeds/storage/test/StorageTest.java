package com.snapmeds.storage.test;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.dosage.Reminder;
import com.snapmeds.AndroidStorage;
import com.snapmeds.MainActivity;
import com.utilities.Dosage;
import com.utilities.DosageParser;
import com.utilities.Drug;
import com.utilities.Frequency;
import com.utilities.Prescription;
import com.utilities.Storage;

public class StorageTest extends ActivityInstrumentationTestCase2<MainActivity>
{
  private Activity activity;
  private Prescription prescription1, prescription2, prescription3;
  private Drug drug1, drug2, drug3;
  
  public StorageTest()
  {
    super(MainActivity.class);
  }
  
  public void setUp() throws Exception
  {
    activity = getActivity();
    AndroidStorage.setContext(activity);
    Storage.getInstance().save(new ArrayList<Prescription>());
    drug1 = new Drug();
    drug1.setSetID("testDrugId1");
    
    drug2 = new Drug();
    drug2.setSetID("testDrugId2");
    
    drug3 = new Drug();
    drug3.setSetID("testDrugId3");
    
    prescription1 = new Prescription();
    prescription1.setDrug(drug1);
    
    prescription2 = new Prescription();
    prescription2.setDrug(drug2);
    
    prescription3 = new Prescription();
    prescription3.setDrug(drug3);
  }
  
  /**
   * Test to make sure loading Prescriptions when no Prescription has been stored results
   * in an empty list of Prescriptions
   * @throws Exception 
   * @throws JsonMappingException 
   * @throws JsonParseException 
   */
  public void testEmptyInitialStorage() throws Exception
  {
    List<Prescription> expectedPrescriptions = new ArrayList<Prescription>();
    List<Prescription> actualPrescriptions = Storage.getInstance().load();
    assertEquals(expectedPrescriptions, actualPrescriptions);
  }
  
  /**
   * Test to make sure that a Prescription added to Storage can be 
   * retrieved from Storage
   * @throws Exception
   */
  public void testAddPrescription() throws Exception
  {
    Storage.getInstance().add(prescription1);
    
    List<Prescription> actualPrescriptions = Storage.getInstance().load();
    List<Prescription> expectedPrescriptions = new ArrayList<Prescription>();
    expectedPrescriptions.add(prescription1);
    
    assertEquals(expectedPrescriptions, actualPrescriptions);
  }
  
  /**
   * Test to make sure individually adding Prescription objects to Storage
   * results in them getting properly persisted
   * @throws Exception
   */
  public void testAddPrescriptionsIndividually() throws Exception
  {
    Storage.getInstance().add(prescription1);
    Storage.getInstance().add(prescription2);
    
    List<Prescription> actualPrescriptions = Storage.getInstance().load();
    List<Prescription> expectedPrescriptions = new ArrayList<Prescription>();
    expectedPrescriptions.add(prescription1);
    expectedPrescriptions.add(prescription2);
    
    assertEquals(expectedPrescriptions, actualPrescriptions);
  }
  
  /**
   * Test to make sure that adding a list of prescriptions to Storage
   * gets properly persisted to Storage
   * @throws Exception
   */
  public void testAddPrescriptions() throws Exception
  {
    List<Prescription> expectedPrescriptions = new ArrayList<Prescription>();
    expectedPrescriptions.add(prescription1);
    expectedPrescriptions.add(prescription2);

    Storage.getInstance().save(expectedPrescriptions);
    
    List<Prescription> actualPrescriptions = Storage.getInstance().load();
    
    assertEquals(expectedPrescriptions, actualPrescriptions);
  }
  
  /**
   * Test to make sure that an edited Prescription object has 
   * the edit persisted to Storage
   * @throws Exception
   */
  public void testEditPrescription() throws Exception
  {
    Storage.getInstance().add(prescription1);
    
    prescription1.setNote("this is a test");
    Storage.getInstance().editPrescription(prescription1);

    List<Prescription> prescriptions = Storage.getInstance().load();
    
    if (prescriptions != null && prescriptions.size() == 1)
    {
      Prescription storedPrescription = prescriptions.get(0);
      boolean isSameId = prescription1.getDrug().getSetID().equals(storedPrescription.getDrug().getSetID());
      boolean wasEdited = prescription1.getNote().equals(storedPrescription.getNote());
      
      assertTrue(isSameId && wasEdited);
    }
    else
    {
      fail();
    }
  }
  
  /**
   * Test to make sure that clearing the Storage actually removes
   * all Prescriptions from Storage
   * @throws Exception
   */
  public void testClearStorage() throws Exception
  {
    Storage.getInstance().add(prescription1);
    Storage.getInstance().add(prescription2);
    Storage.getInstance().save(new ArrayList<Prescription>());
    
    List<Prescription> actualPrescriptions = Storage.getInstance().load();
    List<Prescription> expectedPrescriptions = new ArrayList<Prescription>();
    
    assertEquals(expectedPrescriptions, actualPrescriptions);
  }
  
  public void testDrugDataPersists() throws Exception
  {
    String expectedAdverseReactions = "Test Reactions";
    String expectedBoxWarnings = "Test Box Warnings";
    String expectedGenericNames = "Test Generic Names";
    
	  drug1.setAdverseReactions(expectedAdverseReactions);
	  drug1.setBoxWarnings(expectedBoxWarnings);
	  drug1.setGenericNames(expectedGenericNames);
	  
	  Storage.getInstance().add(prescription1);
	  Drug loadedDrug = Storage.getInstance().load().get(0).getDrug();
	 
	  assertEquals(expectedAdverseReactions, loadedDrug.getAdverseReactions());
	  assertEquals(expectedBoxWarnings, loadedDrug.getBoxWarnings());
	  assertEquals(expectedGenericNames, loadedDrug.getGenericNames());
  }
  
  public void testDosageDataPersists() throws Exception
  {
    String expectedDose = "Five pills";
    String expectedFrequencyString = "twice every three hours";
    String dosageString = String.format("%s %s", expectedDose, expectedFrequencyString);
    Frequency expectedFrequency = new Frequency();
    expectedFrequency.parseString(expectedFrequencyString);
    
	  prescription1.setDosage(DosageParser.parseDosageString(dosageString));
	  
	  Storage.getInstance().add(prescription1);
	  Prescription actualPrescription = Storage.getInstance().load().get(0);
	  Dosage actualDosage = actualPrescription.getDosage();
	  
	  assertEquals(expectedDose, actualDosage.getQuantity());
	  assertEquals(expectedFrequency, actualDosage.getFrequency());
	  assertEquals("", actualDosage.getRoute());
	  assertEquals("", actualDosage.getWarnings());
	  assertEquals("", actualDosage.getInstructions());
	  assertEquals("", actualDosage.getReason());
	  assertEquals(new ArrayList<Reminder>(), actualDosage.getReminders());
  }
}
