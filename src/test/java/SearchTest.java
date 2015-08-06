

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import hello.search.SearchController;
import hello.utils.exceptions.ProgramError;
import junit.framework.TestCase;

public class SearchTest extends TestCase {
	
	@Test
	public void testAllAsciiChars() throws ProgramError, JSONException, IOException {
		SearchController testClass = new SearchController();
		
		for(int i=0; i<128; i++){
			testClass.doSearch("test"+String.valueOf(i));
		}
	}
}
