package uk.ac.ox.oucs.content.metadata.logic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.authz.api.SecurityAdvisor;
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.content.api.ContentHostingService;
import org.sakaiproject.content.api.ContentResource;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.exception.TypeException;
import org.sakaiproject.tool.api.ToolManager;
import uk.ac.ox.oucs.content.metadata.model.MetadataType;

/**
 * @author Colin Hebert
 */
public abstract class MetadataServiceFromContent implements MetadataService
{

	private static Log logger = LogFactory.getLog(MetadataServiceFromContent.class);

	protected final ContentHostingService contentHostingService;
	protected final SecurityService securityService;
	protected final ToolManager toolManager;

	public MetadataServiceFromContent(ContentHostingService contentHostingService, SecurityService securityService, ToolManager toolManager)
	{
		this.contentHostingService = contentHostingService;
		this.securityService = securityService;
		this.toolManager = toolManager;
	}

	public List<MetadataType> getMetadataAvailable(String resourceType)
	{
		try
		{
			InputStream is = forceAccessResource(getGlobalMetaContent()).streamContent();
			//TODO find a way to filter based on resourceType (should be in an AbstractMetadataService?)
			return parse(is);
		}
		catch (IdUnusedException e)
		{
			logger.debug("The metadata configuration file doesn't exist", e);
			return Collections.emptyList();
		}
		catch (TypeException e)
		{
			logger.debug("The metadata configuration file doesn't exist", e);
			return Collections.emptyList();
		}
		catch (Exception e)
		{
			//No exception coming from the file loading shall get out of this API !
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	public List<MetadataType> getMetadataAvailable(String siteId, String resourceType)
	{
		List<MetadataType> metadataTypes = new ArrayList<MetadataType>();
		metadataTypes.addAll(getMetadataAvailable(resourceType));

		try
		{
			InputStream is = forceAccessResource(getSiteMetadataConfigFile(siteId)).streamContent();
			//TODO find a way to filter based on resourceType (should be in an AbstractMetadataService?)

			metadataTypes.addAll(parse(is));
		}
		catch (IdUnusedException e)
		{
			logger.debug("The metadata configuration file doesn't exist", e);
		}
		catch (TypeException e)
		{
			logger.debug("The metadata configuration file doesn't exist", e);
		}
		catch (Exception e)
		{
			//No exception coming from the file loading shall get out of this API !
			logger.error(e.getMessage(), e);
		}

		return metadataTypes;
	}

	private ContentResource forceAccessResource(String contentId) throws IdUnusedException, TypeException, PermissionException
	{
		SecurityAdvisor securityAdvisor = tempReadOnlyAdvisor(contentId);
		securityService.pushAdvisor(securityAdvisor);
		try
		{
			return contentHostingService.getResource(contentId);
		}
		finally
		{
			securityService.popAdvisor(securityAdvisor);
		}
	}

	/**
	 * Generate a temporary advisor to enable reading on a specific content
	 * <p/>
	 * Any use of this advisor should be temporary as it could create a security breach
	 *
	 * @param contentId Content to unlock
	 * @return an advisor allowing read access on the specified content
	 */
	private SecurityAdvisor tempReadOnlyAdvisor(final String contentId)
	{
		return new SecurityAdvisor()
		{
			public SecurityAdvice isAllowed(String userId, String function, String reference)
			{
				//TODO Check userId too ?
				if (ContentHostingService.AUTH_RESOURCE_READ.equals(function) && contentId.equals(reference))
					return SecurityAdvice.ALLOWED;
				else
					return SecurityAdvice.PASS;
			}
		};
	}

	/**
	 * Parse a metadata configuration file
	 *
	 * @param inputStream Configuration file stream
	 * @return A list of metadata obtained in the configuration file
	 */
	protected abstract List<MetadataType> parse(InputStream inputStream);

	/**
	 * Returns the contentId of a file containing a metadata configuration for a specific site
	 *
	 * @param siteId site on which the configuration should be searched
	 * @return a content id
	 */
	protected abstract String getSiteMetadataConfigFile(String siteId);

	/**
	 * Return the contentId of a file containing a metadata configuration for the whole sakai application
	 *
	 * @return a content id
	 */
	protected abstract String getGlobalMetaContent();
}
