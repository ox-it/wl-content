package uk.ac.ox.oucs.content.metadata.model;

import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.User;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.user.api.UserNotDefinedException;

import java.util.Collection;

/**
 * @author Colin Hebert
 */
public class UserMetadataType extends MetadataType<User>
{
	private static UserDirectoryService userDirectoryService;
	private static SiteService siteService;

	public UserMetadataType(UserDirectoryService userDirectoryService, SiteService siteService)
	{
		UserMetadataType.userDirectoryService = userDirectoryService;
		UserMetadataType.siteService = siteService;
	}

	public UserMetadataType()
	{
	}

	public Collection<User> getAllowedValues(String siteId)
	{
		try
		{
			return userDirectoryService.getUsers(siteService.getSite(siteId).getUsers());
		}
		catch (IdUnusedException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public MetadataRenderer getRenderer()
	{
		return new UserMetadataRenderer();
	}

	@Override
	public MetadataConverter<User> getConverter()
	{
		return new UserMetadataConverter();
	}

	@Override
	public MetadataValidator<User> getValidator()
	{
		return new UserMetadataValidator();
	}


	private final class UserMetadataValidator implements MetadataValidator<User>
	{
		public boolean validate(User value)
		{
			return true;
		}
	}

	private final class UserMetadataRenderer implements MetadataRenderer
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
			return "vm/metadata/meta_edit_user.vm";
		}

		public String getMetadataValuePrintTemplate()
		{
			return null;	//To change body of implemented methods use File | Settings | File Templates.
		}
	}

	private final class UserMetadataConverter implements MetadataConverter<User>
	{

		public String toString(User object)
		{
			if(object == null)
				return null;
			return object.getId();
		}

		public User toObject(String string)
		{
			try
			{
				if(string == null || string.isEmpty()){
					return null;
				}

				return userDirectoryService.getUser(string);
			}
			catch (UserNotDefinedException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
