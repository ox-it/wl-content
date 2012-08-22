package uk.ac.ox.oucs.content.metadata.logic;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ox.oucs.content.metadata.model.BooleanMetadataType;
import uk.ac.ox.oucs.content.metadata.model.MetadataConverter;
import uk.ac.ox.oucs.content.metadata.model.MetadataValidator;


public class BooleanMetadataTest {

	private BooleanMetadataType type;

	@Before
	public void setUp() {
		type = new BooleanMetadataType();
		type.setName("test");
		type.setUniqueName("unique");
	}
	
	@Test
	public void testConverter() {
		MetadataConverter<Boolean> converter = type.getConverter();
		// When coming from a HTTP request we don't care what the value is, just that it's present.
		assertEquals(true, converter.fromHttpForm(Collections.singletonMap("unique1", "value"), "1"));
		assertEquals(true, converter.fromHttpForm(Collections.singletonMap("unique1", "true"), "1"));
		assertEquals(false, converter.fromHttpForm(Collections.singletonMap("other", "other"), "1"));
		
		// Test  string handling
		assertEquals(true,converter.fromString("true"));
		assertEquals(false,converter.fromString("false"));
		assertEquals(false,converter.fromString("on"));
		assertEquals(null,converter.fromString(null));


		// Test properties.
		assertEquals(true, converter.fromProperties(Collections.singletonMap("unique", "true")));
		assertEquals(false, converter.fromProperties(Collections.singletonMap("unique", "false")));
		assertEquals(false, converter.fromProperties(Collections.singletonMap("unique", "bad")));
		assertEquals(null, converter.fromProperties(Collections.singletonMap("wrong", "true")));
	}
	
	@Test
	public void testValidator() {
		// Check that required basically doesn't accept null.
		type.setRequired(false);
		MetadataValidator<Boolean> validator = type.getValidator();
		assertTrue(validator.validate(null));
		assertTrue(validator.validate(true));
		assertTrue(validator.validate(false));
		type.setRequired(true);
		assertFalse(validator.validate(null));
		assertTrue(validator.validate(true));
		assertTrue(validator.validate(false));
	}
}
