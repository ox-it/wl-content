package uk.ac.ox.oucs.content.metadata.logic;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ox.oucs.content.metadata.model.MetadataType;


public class JsonMetadataParserTest {

	private MetadataParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new JsonMetadataParser();
	}

	@Test
	public void testGoodParse() {
		InputStream in = getClass().getResourceAsStream("/simple-metadata.json");
		assertNotNull(in);
		List<MetadataType> parse = parser.parse(in);
		assertNotNull(parse);
		assertTrue(parse.size() > 0);
	}

}
