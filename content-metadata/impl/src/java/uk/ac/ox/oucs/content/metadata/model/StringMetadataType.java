package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collections;
import java.util.Map;

/**
 * @author Colin Hebert
 */
public class StringMetadataType extends MetadataType<String>
{
	private int minLength;
	private int maxLength;
	private String regularExpression;
	//Renders textareas instead of textboxes
	private boolean longText;

	@Override
	public MetadataRenderer getRenderer()
	{
		return new StringMetadataRenderer();
	}

	@Override
	public MetadataConverter<String> getConverter()
	{
		return new StringMetadataConverter();
	}

	@Override
	public MetadataValidator<String> getValidator()
	{
		return new StringMetadataValidator();
	}

	private final class StringMetadataValidator implements MetadataValidator<String>
	{
		public boolean validate(String value)
		{
			if (value == null || value.isEmpty())
				return isRequired();
			if (minLength > 0 && value.length() < minLength)
				return false;
			if (maxLength > 0 && value.length() > maxLength)
				return false;
			if (regularExpression != null && !value.matches(regularExpression))
				return false;
			return true;
		}
	}

	protected final class StringMetadataConverter implements MetadataConverter<String>
	{
		public String toString(String object)
		{
			return object;
		}

		public String toObject(String string)
		{
			return string;
		}

		public Map<Object, Object> toProperties(String object)
		{
			return Collections.<Object, Object>singletonMap(getUuid(), toString(object));
		}

		public String toObject(Map properties, String propertySuffix)
		{
			return (String) properties.get(getUuid() + propertySuffix);
		}
	}

	private final static class StringMetadataRenderer implements MetadataRenderer
	{

		public String getMetadataTypeEditTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataTypePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}

		public String getMetadataValueEditTemplate()
		{
			return "vm/metadata/meta_edit_text.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}
}
