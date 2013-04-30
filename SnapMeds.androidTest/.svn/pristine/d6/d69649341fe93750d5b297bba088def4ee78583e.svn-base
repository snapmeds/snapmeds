/*package com.snapmeds.dosage.test;

import android.content.Context;

import com.dosage.Reminder;
import com.utilities.Frequency;

public class ReminderTest {
	private static final FakeReminder reminder1 = new FakeReminder(null,
			"title", "detail");
	private static final FakeReminder reminder2 = new FakeReminder(null,
			"title", "detail");
	
	private static final Frequency FREQUENCY = new Frequency();
	private static final boolean SETUP = true;
	private static final boolean DIFFERENT_SETUP = false;

	private static class FakeReminder extends Reminder {
		public FakeReminder(Frequency frequency, String title, String detail) {
			super(frequency, title, detail);
		}

		@Override
		public void setupReminder(Context context) {

		}

		@Override
		public void cancelReminder(Context context) {

		}
	}

	public static void populateFakeReminder(Reminder reminder) {
		reminder.setDetail("detail");
		reminder.setFrequency(FREQUENCY);
		reminder.setSetup(SETUP);
		reminder.setTitle("title");
	}

	@Before
	public static void reset() {
		populateFakeReminder(reminder1);
		populateFakeReminder(reminder2);
	}

	@Test
	public void testEqualsWorks() {
		assertTrue(reminder1.equals(reminder2));
	}
	
	@Test
	public void testEqualsHandlesNull() {
		assertFalse(reminder1.equals(null));
	}
	
	@Test
	public void testEqualsChecksDetail() {
		reminder1.setDetail("different");
		assertFalse(reminder1.equals(reminder2));
	}
	
	@Test
	public void testEqualsChecksFrequency() {
		reminder1.setFrequency(null);
		assertFalse(reminder1.equals(reminder2));
	}
	
	@Test
	public void testEqualsChecksSetup() {
		reminder1.setSetup(DIFFERENT_SETUP);
		assertFalse(reminder1.equals(reminder2));
	}
	
	@Test
	public void testEqualsChecksTitle() {
		reminder1.setTitle("different");
		assertFalse(reminder1.equals(reminder2));
	}
}*/
