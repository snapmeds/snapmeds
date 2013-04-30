package com.snapmeds.storage.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.snapmeds.DrugDetailActivity;
import com.snapmeds.MainActivity;
import com.utilities.Drug;
import com.utilities.Prescription;
import com.utilities.Storage;

public class MultiActivityStorageTest extends InstrumentationTestCase
{
  private Activity _activity1, _activity2;
  private Prescription _prescription;
  private Drug _drug;
  
  public MultiActivityStorageTest()
  {}
  
  public void setUp() throws Exception
  {
    _activity1 = launchActivity("com.snapmeds", MainActivity.class, null);
    Storage.getInstance().save(new ArrayList<Prescription>());
    _prescription = new Prescription();
    _drug = new Drug();
    _drug.setSetID("54");
    _prescription.setDrug(_drug);
  }
  
  public void testMutliActivityWriteRead() throws Exception
  {
    List<Prescription> expectedDosages = new ArrayList<Prescription>();
    expectedDosages.add(_prescription);
    Storage.getInstance().add(_prescription);
    _activity1.finish();
    _activity2 = launchActivity("com.snapmeds", DrugDetailActivity.class, null);
    List<Prescription> actualDosages = Storage.getInstance().load();
    _activity2.finish();
    
    assertEquals(expectedDosages, actualDosages);
  }
}