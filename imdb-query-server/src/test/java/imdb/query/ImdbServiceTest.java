package imdb.query;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.imdb.query.service.ImdbService;

public class ImdbServiceTest {
	
	private ImdbService imdbService;
	
	@BeforeEach
	void setUp() {
		imdbService = new ImdbService();
	}
	
	@Test
	void testSearchExistTitle() throws IOException {
		String result = imdbService.fetchHtml("4:king");
		assertEquals("200", result.split(":")[0]);
		assertEquals("King (2008)", result.split("\n")[2]);
	}
	
	@Test
	void testSearchNonExistTitle() throws IOException {
		String result = imdbService.fetchHtml("10:mklsamdkls");
		assertNull(result);
	}
}	
