package uk.ac.ox.oucs.content.metadata.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Colin Hebert
 */
public class GroupMetadataType extends MetadataType<Map<String, ?>>
{
	//TODO : Provide a way to enable ordering ?
	private List<MetadataType<?>> metadataTypes;

	public List<MetadataType<?>> getMetadataTypes()
	{
		return metadataTypes;
	}

	public void setMetadataTypes(List<MetadataType<?>> metadataTypes)
	{
		this.metadataTypes = metadataTypes;
	}

	@Override
	public MetadataRenderer getRenderer()
	{
		return new GroupMetadataRenderer();
	}

	@Override
	public MetadataConverter<Map<String, ?>> getConverter()
	{
		return new GroupMetadataConverter();
	}

	@Override
	public MetadataValidator<Map<String, ?>> getValidator()
	{
		return new GroupMetadataValidator();
	}

	private final class GroupMetadataConverter implements MetadataConverter<Map<String, ?>>
	{

		/**
		 * {@inheritDoc}
		 * <p/>
		 * INFO: For the suppress warning, see the content of the method
		 *
		 * @param metaValues {@inheritDoc}
		 * @return {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		public String toString(Map<String, ?> metaValues)
		{
			try
			{
				if (metaValues == null)
					return null;

				Map<String, String> stringValues = new HashMap<String, String>(metaValues.size());
				for (MetadataType metadataType : metadataTypes)
				{
					String id = metadataType.getUuid();
					/*
					 * There is no way to be sure of the metadata type of the entry, so a "cast" is required.
					 * In this case we can't cast to "?" so here goes some unchecked operations.
					 */
					String converted = metadataType.getConverter().toString(metaValues.get(id)); //We do it live!
					if (converted != null)
						stringValues.put(id, converted);
				}
				return new ObjectMapper().writeValueAsString(stringValues);
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}

		public Map<String, ?> toObject(String string)
		{
			try
			{
				if (string == null)
					return null;
				Map<String, String> stringValues = new ObjectMapper().readValue(string, new TypeReference<Map<String, String>>()
				{
				});

				Map<String, Object> metaValues = new HashMap<String, Object>(stringValues.size());

				for (MetadataType metadataType : metadataTypes)
				{
					String uuid = metadataType.getUuid();
					Object converted = metadataType.getConverter().toObject(stringValues.get(uuid));
					if (converted != null)
						metaValues.put(uuid, converted);
				}

				return metaValues;
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	private final class GroupMetadataValidator implements MetadataValidator<Map<String, ?>>
	{
		/**
		 * {@inheritDoc}
		 * <p/>
		 * INFO: For the suppress warning, see the content of the method
		 *
		 * @param object {@inheritDoc}
		 * @return {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		public boolean validate(Map<String, ?> object)
		{
			for (MetadataType metadataType : metadataTypes)
			{
				String uuid = metadataType.getUuid();
				/*
				 * There is no way to be sure of the metadata type of the entry, so a "cast" is required.
				 * In this case we can't cast to "?" so here goes some unchecked operations.
				 */
				if (!metadataType.getValidator().validate(object.get(uuid)))
				{
					return false;
				}
			}

			return true;
		}
	}

	private class GroupMetadataRenderer implements MetadataRenderer {
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
			return "vm/metadata/meta_edit_group.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}
}
