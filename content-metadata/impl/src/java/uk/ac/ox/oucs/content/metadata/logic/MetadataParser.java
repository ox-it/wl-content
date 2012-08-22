package uk.ac.ox.oucs.content.metadata.logic;

import java.io.InputStream;
import java.util.List;

import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * Interface to be implemented by a class that wishes to parse some metadata from an InputStream.
 * @author buckett
 *
 */
public interface MetadataParser {

	public List<MetadataType> parse(InputStream inputStream);

	
}