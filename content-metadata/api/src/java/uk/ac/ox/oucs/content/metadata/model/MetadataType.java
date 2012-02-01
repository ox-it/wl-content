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
	private String id;

	/**
	 * Name used in the view layer
	 */
	private String name;

	/**
	 * Simple description
	 */
	private String description;

	/**
	 * Are the name an description translated (in properties files)
	 */
	private boolean translated;

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


	public String getUniqueName()
	{
		return id;
	}

	public void setUniqueName(String uniqueName)
	{
		this.id = uniqueName;
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

	public boolean isTranslated()
	{
		return translated;
	}

	public void setTranslated(boolean translated)
	{
		this.translated = translated;
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
