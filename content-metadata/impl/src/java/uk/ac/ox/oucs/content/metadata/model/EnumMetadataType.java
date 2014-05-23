package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author Colin Hebert
 */
public class EnumMetadataType extends MetadataType<String>
{
	private Collection<String> allowedValues;

	public Collection<String> getAllowedValues()
	{
		return allowedValues;
	}

	public void setAllowedValues(Collection<String> allowedValues)
	{
		this.allowedValues = allowedValues;
	}

	/**
	 * This gets the label that should used when displaying the values.
	 * @param value The value to lookup the label for.
	 * @return The label to show the user or the i18n key if it's a translated type.
	 */
	public String getValueLabel(String value) {
		if (isTranslated()) {
			return getName() + "." + value;
		} else {
			return value;
		}
	}

	@Override
	public MetadataRenderer getRenderer()
	{
		return new EnumMetadataRenderer();
	}

	@Override
	public MetadataConverter<String> getConverter()
	{
		return new EnumMetadataConverter();
	}

	@Override
	public MetadataValidator<String> getValidator()
	{
		return new EnumMetadataValidator();
	}


	private final class EnumMetadataValidator implements MetadataValidator<String>
	{
		public boolean validate(String metadataValue)
		{
			if (metadataValue == null)
				return isRequired();
			if (allowedValues != null && allowedValues.contains(metadataValue))
				return false;

			return true;
		}
	}

	private final class EnumMetadataRenderer implements MetadataRenderer
	{
		public String getMetadataTypeEditTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataTypeDisplayTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataValueEditTemplate()
		{
			return "meta_edit_enum";
		}

		public String getMetadataValueDisplayTemplate()
		{
			return "meta_display_string";
		}
	}

	protected final class EnumMetadataConverter implements MetadataConverter<String>
	{
		public String toString(String metadataValue)
		{
			return (metadataValue != null && !metadataValue.isEmpty()) ? metadataValue : null;
		}

		public String fromString(String stringValue)
		{
			return (stringValue != null && !stringValue.isEmpty()) ? stringValue : null;
		}

		public Map<String, ?> toProperties(String metadataValue)
		{
			String stringValue = toString(metadataValue);
			return Collections.singletonMap(getUniqueName(), stringValue);
		}

		public String fromProperties(Map<String, ?> properties)
		{
			return fromString((String) properties.get(getUniqueName()));
		}

		public String fromHttpForm(Map<String, ?> parameters, String parameterSuffix)
		{
			return fromString((String) parameters.get(getUniqueName() + parameterSuffix));
		}
	}
}
