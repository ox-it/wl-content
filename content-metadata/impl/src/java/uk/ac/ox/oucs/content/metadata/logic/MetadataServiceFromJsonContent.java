package uk.ac.ox.oucs.content.metadata.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.content.api.ContentEntity;
import org.sakaiproject.content.api.ContentHostingService;
import org.sakaiproject.tool.api.ToolManager;
import uk.ac.ox.oucs.content.metadata.mixins.MetadataTypeMixin;
import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * @author Colin Hebert
 */
public class MetadataServiceFromJsonContent extends MetadataServiceFromContent
{

	@Override
	protected List<MetadataType> parse(InputStream inputStream)
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
			Thread.currentThread().setContextClassLoader(MetadataServiceFromJsonContent.class.getClassLoader());
			return objectMapper.readValue(inputStream, new TypeReference<List<MetadataType>>() {});
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		finally {
			Thread.currentThread().setContextClassLoader(cl);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * Look for metadata definition in /metadata/metadata.json
	 *
	 * @param siteId {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	protected String getSiteMetaContent(String siteId)
	{
		toolManager.getCurrentPlacement().getPlacementConfig().getProperty("home");
		String siteRoot = contentHostingService.getSiteCollection(siteId);
		ContentEntity metaFolder = getSubContent(forceAccessCollection(siteRoot), "metadata");
		ContentEntity file = getSubContent(forceAccessCollection(metaFolder.getId()), "metadata.json");
		return file.getId();
	}

	@Override
	protected String getGlobalMetaContent()
	{
		return "";
	}

	public MetadataServiceFromJsonContent(ContentHostingService contentHostingService, SecurityService securityService, ToolManager toolManager)
	{
		super(contentHostingService, securityService, toolManager);
	}
}
