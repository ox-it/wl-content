package uk.ac.ox.oucs.content.metadata.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import uk.ac.ox.oucs.content.metadata.mixins.ListMetadataTypeMixin;
import uk.ac.ox.oucs.content.metadata.mixins.MetadataTypeMixin;
import uk.ac.ox.oucs.content.metadata.model.ListMetadataType;
import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * A simple parser that takes a JSON input stream and returns the MetadataTypes.
 * @author buckett
 *
 */
public class JsonMetadataParser implements MetadataParser {

	public List<MetadataType> parse(InputStream inputStream)
	{
		/**
		 *  FIXME: The ContextClassLoader is switched in order to work with {@link org.codehaus.jackson.map.jsontype.impl#typeFromId(String)}
		 *  The current ContextClassLoader is the one from the tool making the call (ie. ContentTool) so it doesn't contain the actual implementation of metadatatypes
		 *  The classloader is switched back later in the finally clause (as it HAS to be restored)
		 *
		 *  See JACKSON-350.
		 */
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getDeserializationConfig().addMixInAnnotations(MetadataType.class, MetadataTypeMixin.class);
			objectMapper.getDeserializationConfig().addMixInAnnotations(ListMetadataType.class, ListMetadataTypeMixin.class);
			Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
			return objectMapper.readValue(inputStream, new TypeReference<List<MetadataType>>() {});
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			Thread.currentThread().setContextClassLoader(cl);
		}
	}


}
