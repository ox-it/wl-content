package uk.ac.ox.oucs.content.metadata.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.*;

/**
 * @author Colin Hebert
 */
public class ListMetadataType<T> extends MetadataType<List<T>>
{
	private final MetadataType<T> metadataType;
	private List<T> defaultValue;

	public ListMetadataType(MetadataType<T> metadataType)
	{
		this.metadataType = metadataType;
	}

	public MetadataType<T> getMetadataType()
	{
		return metadataType;
	}

	@Override
	public String getUuid()
	{
		return metadataType.getUuid();
	}

	@Override
	public void setUuid(String uuid)
	{
		metadataType.setUuid(uuid);
	}

	public String getName()
	{
		return metadataType.getName();
	}

	public void setName(String name)
	{
		metadataType.setName(name);
	}

	public String getDescription()
	{
		return metadataType.getDescription();
	}

	public void setDescription(String description)
	{
		metadataType.setDescription(description);
	}

	public List<T> getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(List<T> defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public boolean isRequired()
	{
		return metadataType.isRequired();
	}

	public void setRequired(boolean required)
	{
		metadataType.setRequired(required);
	}

	public List<String> getContentTypeApplicable()
	{
		return metadataType.getContentTypeApplicable();
	}

	public void setContentTypeApplicable(List<String> contentTypeApplicable)
	{
		metadataType.setContentTypeApplicable(contentTypeApplicable);
	}

	public String getXmlElement()
	{
		return metadataType.getXmlElement();
	}

	public void setXmlElement(String xmlElement)
	{
		metadataType.setXmlElement(xmlElement);
	}

	public String getXmlType()
	{
		return metadataType.getXmlType();
	}

	public void setXmlType(String xmlType)
	{
		metadataType.setXmlType(xmlType);
	}

	@Override
	public MetadataRenderer getRenderer()
	{
		return new ListMetadataRenderer();
	}

	@Override
	public MetadataConverter<List<T>> getConverter()
	{
		return new ListMetadataConverter();
	}

	@Override
	public MetadataValidator<List<T>> getValidator()
	{
		return new ListMetadataValidator();
	}

	//Convert lists to JSon because it's readable and it can be stored in a simple String
	private final class ListMetadataConverter implements MetadataConverter<List<T>>
	{
		MetadataConverter<T> metadataConverter = metadataType.getConverter();

		public String toString(List<T> object)
		{
			try
			{
				if (object == null || object.isEmpty())
					return null;

				List<String> values = new ArrayList<String>(object.size());
				for (T item : object)
				{
					String converted = metadataConverter.toString(item);
					if (converted != null)
						values.add(converted);
				}
				return (!values.isEmpty()) ? new ObjectMapper().writeValueAsString(values) : null;
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}

		public List<T> toObject(String string)
		{
			try
			{
				if (string == null)
				{
					return null;
				}

				List<String> values = new ObjectMapper().readValue(string, new TypeReference<List<String>>()
				{
				});
				List<T> objects = new ArrayList<T>(values.size());

				for (String value : values)
				{
					T converted = metadataConverter.toObject(value);
					if (converted != null)
						objects.add(converted);
				}
				return objects;
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}

		//TODO: Finish the to object from properties
		public List<T> toObject(Map properties, String propertySuffix)
		{
			List<T> list = new ArrayList<T>();
			String[] stringValues;
			Object o = properties.get(getUuid() + propertySuffix);
			if (o == null)
				return list;
			else if (o instanceof String)
				stringValues = new String[]{(String) o};
			else
				stringValues = (String[]) o;

			//Workaround to confuse the Metadata making it think that it's getting an properties map
			Properties fakeProperty = new Properties();
			for (String value : stringValues)
			{
				T converted = metadataType.getConverter().toObject(Collections.<Object, Object>singletonMap(getUuid(), value), "");
				if (converted != null)
					list.add(converted);
			}
			return list;
		}
	}

	private final class ListMetadataValidator implements MetadataValidator<List<T>>
	{
		MetadataValidator<T> metadataValidator = metadataType.getValidator();

		public boolean validate(List<T> objects)
		{
			for (T item : objects)
			{
				if (!metadataValidator.validate(item))
					return false;
			}
			return true;
		}
	}

	private class ListMetadataRenderer implements MetadataRenderer
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
			return "vm/metadata/meta_edit_list.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}
}
