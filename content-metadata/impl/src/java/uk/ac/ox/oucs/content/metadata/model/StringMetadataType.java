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

	public int getMinLength()
	{
		return minLength;
	}

	public void setMinLength(int minLength)
	{
		this.minLength = minLength;
	}

	public int getMaxLength()
	{
		return maxLength;
	}

	public void setMaxLength(int maxLength)
	{
		this.maxLength = maxLength;
	}

	public String getRegularExpression()
	{
		return regularExpression;
	}

	public void setRegularExpression(String regularExpression)
	{
		this.regularExpression = regularExpression;
	}

	public boolean isLongText()
	{
		return longText;
	}

	public void setLongText(boolean longText)
	{
		this.longText = longText;
	}

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
		public boolean validate(String metadataValue)
		{
			if (metadataValue == null || metadataValue.isEmpty())
				return isRequired();
			if (minLength > 0 && metadataValue.length() < minLength)
				return false;
			if (maxLength > 0 && metadataValue.length() > maxLength)
				return false;
			if (regularExpression != null && !metadataValue.matches(regularExpression))
				return false;
			return true;
		}
	}

	protected final class StringMetadataConverter implements MetadataConverter<String>
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
			return (stringValue != null) ? Collections.singletonMap(getUniqueName(), stringValue) : Collections.<String, Object>emptyMap();
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
			return "vm/metadata/meta_edit_string.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}
}
