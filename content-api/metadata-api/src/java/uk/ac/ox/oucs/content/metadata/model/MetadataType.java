package uk.ac.ox.oucs.content.metadata.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Colin Hebert
 */
public abstract class MetadataType<T> implements Serializable
{
	private String uuid;
	private String name;
	private String description;
	private T defaultValue;
	private boolean required;
	private List<String> contentTypeApplicable;

	private String xmlElement;
	private String xmlType;

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public T getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public boolean isRequired()
	{
		return required;
	}

	public void setRequired(boolean required)
	{
		this.required = required;
	}

	public List<String> getContentTypeApplicable()
	{
		return contentTypeApplicable;
	}

	public void setContentTypeApplicable(List<String> contentTypeApplicable)
	{
		this.contentTypeApplicable = contentTypeApplicable;
	}

	public String getXmlElement()
	{
		return xmlElement;
	}

	public void setXmlElement(String xmlElement)
	{
		this.xmlElement = xmlElement;
	}

	public String getXmlType()
	{
		return xmlType;
	}

	public void setXmlType(String xmlType)
	{
		this.xmlType = xmlType;
	}

	public abstract MetadataRenderer getRenderer();

	public abstract MetadataConverter<T> getConverter();

	public abstract MetadataValidator<T> getValidator();

}
