package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collections;
import java.util.Map;

/**
 * Metadata type that supports a boolean type, rendering it as a checkbox in HTML.
 * @author buckett
 *
 */
public class BooleanMetadataType extends MetadataType<Boolean> {

	private static final long serialVersionUID = 1L;

	@Override
	public MetadataRenderer getRenderer() {
		return new BooleanMetadataRender();
	}

	@Override
	public MetadataConverter<Boolean> getConverter() {
		return new BooleanMetadataConverter();
	}

	@Override
	public MetadataValidator<Boolean> getValidator() {
		return new BooleanMetadataValidator();
	}
	
	private final class BooleanMetadataConverter implements MetadataConverter<Boolean> {

		public String toString(Boolean metaValue) {
			return ( metaValue != null) ? metaValue.toString() : null;
		}

		public Boolean fromString(String stringValue) {
			return stringValue != null ? Boolean.valueOf(stringValue) : null;
		}

		public Map<String, ?> toProperties(Boolean metaValue) {
			String value = toString(metaValue);
			return (value != null) ? Collections.singletonMap(getUniqueName(), value) : Collections.<String, Object>emptyMap();
		}

		public Boolean fromProperties(Map<String, ?> properties) {
			return fromString((String) properties.get(getUniqueName()));
		}

		public Boolean fromHttpForm(Map<String, ?> parameters, String parameterSuffix) {
			return Boolean.valueOf(parameters.containsKey(getUniqueName()+ parameterSuffix));
		}

	}
	
	private final class BooleanMetadataValidator implements MetadataValidator<Boolean> {

		public boolean validate(Boolean metadataValue) {
			if (metadataValue == null)
				return !isRequired();
			return true;
		}
		
	}
	
	private final class BooleanMetadataRender implements MetadataRenderer {

		public String getMetadataTypeEditTemplate() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getMetadataTypeDisplayTemplate() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getMetadataValueEditTemplate() {
			return "meta_edit_boolean";
		}

		public String getMetadataValueDisplayTemplate() {
			return "meta_display_boolean";
		}
		
	}

}
