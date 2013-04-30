package queryTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.query.Queryer;
import com.utilities.Drug;

public class QueryerTest {
	
	@Test
	public void testReadURL(){
		QueryerTestClass qTest = new QueryerTestClass();
		String URL = "http://dailymed.nlm.nih.gov/dailymed/services/v1/ndc/0067-2000-91/spls.json";
		String expectedString = "{\"COLUMNS\":[\"SETID\",\"SPL_VERSION\",\"TITLE\",\"PUBLISHED_DATE\"],\"DATA\":[[\"a71074a6-cc19-4bb3-8c84-4e82a8906801\",1,\"EXCEDRIN EXTRA STRENGTH PAIN RELIEVER (ACETAMINOPHEN, ASPIRIN (NSAID), AND CAFFEINE) TABLET, FILM COATED [NOVARTIS CONSUMER HEALTH, INC.]\",\"October 9, 2010\"]]}";
		assertEquals(qTest.readURL(URL), expectedString);
	}

	/**
 	* QueryerTestClass is a sample class to extend queryer
 	* This dummy class does not implement the query functionality
 	* but allows testing of the base class.
 	*
 	*/
	private class QueryerTestClass extends Queryer{
		
		public String readURL(String url){
			return super.readURL(url);
		}

		@Override
		public Drug getDrugFromNDC(String ndc) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected String getSPLsURLFromNDC(String ndc) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected String getImprintDataURLFromNDC(String ndc) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected String getSPLsURLFromDrugname(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Drug> getDrugsFromNDC(String ndc) {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
