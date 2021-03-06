package dosageTest;

import static org.junit.Assert.*;
import org.junit.Test;
import com.utilities.*;

public class DosageTest {

    @Test
    public void testSimpleDosage() {
        Dosage dosage = DosageParser.parseDosageString( "Take one tablet every day." );
        compareFields( dosage, 
        		"One tablet", // Dose
                "", // Route
                "", // Instructions
                "every day", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testTeaspoonsDose() {
        Dosage dosage = DosageParser.parseDosageString( "Take two teaspoonfuls every six hours." );
        compareFields( dosage, 
        		"Two teaspoonfuls", // Dose
                "", // Route
                "", // Instructions
                "every six hours", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testWarnings() {
        Dosage dosage = DosageParser.parseDosageString( "Take one tablet with a glass of water once a day. Avoid eating grapefruit or drinking grapefruit juice." );
        compareFields(
                dosage,
                "One tablet", // Dose
                "", // Route
                "With a glass of water", // Instructions
                "once a day", // Frequency
                "", // Duration
                "", // Reason
                "Avoid eating grapefruit or drinking grapefruit juice" ); // Warnings
    }

    @Test
    public void testInsertInsteadOfTake() {
        Dosage dosage = DosageParser.parseDosageString( "Insert one suppository vaginally every night at bedtime." );
        compareFields( dosage,
                "One suppository", // Dose
                "Vaginally", // Route
                "", // Instructions
                "every night at bedtime", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testReason() {
        Dosage dosage = DosageParser.parseDosageString( "Take one tablet every four to six hours as needed for pain." );
        compareFields( dosage,
                "One tablet", // Dose
                "", // Route
                "", // Instructions
                "every four to six hours as needed", // Frequency
                "", // Duration
                "For pain", // Reason
                "" ); // Warnings
    }

    @Test
    public void testMlDose() {
        Dosage dosage = DosageParser.parseDosageString( "Take one 5ml spoonful four times a day." );
        compareFields( dosage, 
                "One 5ml spoonful", // Dose
                "", // Route
                "", // Instructions
                "four times a day", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testDigitInsteadOfWord() {
        Dosage dosage = DosageParser.parseDosageString( "Take 1 tablet in the morning for high blood pressure." );
        compareFields( dosage,
                "1 tablet", // Dose
                "", // Route
                "", // Instructions
                "in the morning", // Frequency
                "", // Duration
                "For high blood pressure", // Reason
                "" ); // Warnings
    }

    @Test
    public void testWith() {
        Dosage dosage = DosageParser.parseDosageString( "Take 1 tablet with food 3 times per day avoid alcohol." );
        compareFields( dosage,
                "1 tablet", // Dose
                "", // Route
                "With food", // Instructions
                "3 times per day", // Frequency
                "", // Duration
                "", // Reason
                "Avoid alcohol" ); // Warnings
    }

    @Test
    public void testRoute() {
        Dosage dosage = DosageParser.parseDosageString( "Take one tablet by mouth, daily." );
        compareFields( dosage, 
        		"One tablet", // Dose
                "By mouth", // Route
                "", // Instructions
                "daily", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testDurationWithUnnecessaryInfo() {
        Dosage dosage = DosageParser.parseDosageString( "50mg once daily with food, with antiplatelet doses of aspirin, for up to 30 days following stent implantation." );
        compareFields(
                dosage,
                "50mg", // Dose
                "", // Route
                "With food with antiplatelet doses of aspirin", // Instructions
                "once daily", // Frequency
                "For up to 30 days following stent implantation", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testDifferentFrequencyWording() {
        Dosage dosage = DosageParser.parseDosageString( "Take one tablet by mouth one time daily." );
        compareFields( dosage, 
        		"One tablet", // Dose
                "By mouth", // Route
                "", // Instructions
                "one time daily", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testCapsule() {
        Dosage dosage = DosageParser.parseDosageString( "Take one capsule by mouth three times daily." );
        compareFields( dosage,
                "One capsule", // Dose
                "By mouth", // Route
                "", // Instructions
                "three times daily", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testGiveChildInsteadOfTake() {
        Dosage dosage = DosageParser.parseDosageString( "Give child 3/4 teaspoonful by mouth twice a day for 5 days." );
        compareFields( dosage,
                "3/4 teaspoonful", // Dose
                "By mouth", // Route
                "", //Instructions
                "twice a day", // Frequency
                "For 5 days", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testDuration() {
        Dosage dosage = DosageParser.parseDosageString( "Give one tablet by mouth once per day until gone." );
        compareFields( dosage,
                "One tablet", // Dose
                "By mouth", // Route
                "", // Instructions
                "once per day", // Frequency
                "Until gone", // Duration
                "", // Reason
                "" ); // Warnings
    }

    @Test
    public void testLongDuration() {
        Dosage dosage = DosageParser.parseDosageString( "Take one capsule by mouth three times daily for 10 days until all taken." );
        compareFields(
                dosage,
                "One capsule", // Dose
                "By mouth", // Route
                "", // Instructions
                "three times daily", // Frequency
                "For 10 days until all taken", // Duration
                "", // Reason
                "" ); // Warnings
    }
    
    @Test
    public void testLongFrequency() {
        Dosage dosage = DosageParser.parseDosageString( "Take 1 tablet daily the same time each day." );
        compareFields( dosage,
                "1 tablet", // Dose
                "", // Route
                "The same time each day", // Instructions
                "daily", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }
    
    @Test
    public void testComplexRoute() {
        Dosage dosage = DosageParser.parseDosageString( "Take one puff in each nostril twice daily." );
        compareFields( dosage,
                "One puff", // Dose
                "In each nostril", // Route
                "", // Instructions
                "twice daily", // Frequency
                "", // Duration
                "", // Reason
                "" ); // Warnings
    }

    private void compareFields( Dosage dosage, String dose, String route, String args, String frequency, String duration,
            String reason, String warnings ) {
        assertTrue( dosage != null );
        assertEquals( dose, dosage.getQuantity() );
        assertEquals( route, dosage.getRoute() );
        assertEquals( args, dosage.getInstructions() );
        assertEquals( reason, dosage.getReason() );
        assertEquals( warnings, dosage.getWarnings() );
    }
}
