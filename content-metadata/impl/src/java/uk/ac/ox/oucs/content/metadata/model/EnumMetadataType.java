package uk.ac.ox.oucs.content.metadata.model;

import java.util.Collection;

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

	@Override
	public MetadataRenderer getRenderer()
	{
		return new EnumMetadataRenderer();
	}

	@Override
	public MetadataConverter<String> getConverter()
	{
		return StringMetadataType.metadataConverter;
	}

	@Override
	public MetadataValidator<String> getValidator()
	{
		return new EnumMetadataValidator();
	}


	private final class EnumMetadataValidator implements MetadataValidator<String>
	{
		public boolean validate(String value)
		{
			if (value == null) return isRequired();
			if (allowedValues != null && allowedValues.contains(value)) return false;

			return true;
		}
	}

	private final class EnumMetadataRenderer implements MetadataRenderer
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
			return "vm/metadata/meta_edit_enum.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}

}
