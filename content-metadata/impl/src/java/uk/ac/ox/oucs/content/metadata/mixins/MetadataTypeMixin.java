package uk.ac.ox.oucs.content.metadata.mixins;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Jackson description for {@link uk.ac.ox.oucs.content.metadata.model.MetadataType}
 * <p/>
 * Ignore getters/setters/constructors, seek for every field (even private fields)
 * Store the current class in "@class"
 * Ignore default values
 *
 * @author Colin Hebert
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonAutoDetect(value = JsonMethod.FIELD,
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		creatorVisibility = JsonAutoDetect.Visibility.NONE,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		isGetterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public interface MetadataTypeMixin
{
}
