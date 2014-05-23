package uk.ac.ox.oucs.content.metadata.mixins;

import org.codehaus.jackson.annotate.*;
import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * @author Colin Hebert
 */
public abstract class ListMetadataTypeMixin implements MetadataTypeMixin{
	public ListMetadataTypeMixin(@JsonProperty("metadataType")MetadataType metadataType){}
}
