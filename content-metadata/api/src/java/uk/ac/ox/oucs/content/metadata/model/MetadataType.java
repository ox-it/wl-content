package uk.ac.ox.oucs.content.metadata.model;

import java.io.Serializable;
import java.util.List;

/**
 * Description of a metadata field, specifies its values, constraints and provides tools for validation, rendering and conversion
 *
 * @author Colin Hebert
 */
public abstract class MetadataType<T> implements Serializable
{
	/**
	 * Unique name used to identify the Metadata field
	 */
	private String uuid;

	/**
	 * Name used in the view layer
	 */
	private String name;

	/**
	 * Simple description
	 */
	private String description;

	/**
	 * Default value
	 */
	private T defaultValue;

	/**
	 * Optionality of the field
	 */
	private boolean required;

	/**
	 * List of contentTypes using this metadata
	 */
	private List<String> contentTypeApplicable;

	/**
	 * Backward compatibility with DublinCore
	 */
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

	/**
	 * Gets a rendering tool allowing to select a template for this Metadata depending on the current action
	 *
	 * @return A {@link MetadataRenderer} customizing the rendering
	 */
	public abstract MetadataRenderer getRenderer();

	/**
	 * Gets a conversion tool describing the basic conversions used by this metadata
	 *
	 * @return A {@link MetadataConverter}
	 */
	public abstract MetadataConverter<T> getConverter();

	/**
	 * Gets a validation tool to enforce constraints on the metadata values
	 *
	 * @return A {@link MetadataConverter}
	 */
	public abstract MetadataValidator<T> getValidator();

}
