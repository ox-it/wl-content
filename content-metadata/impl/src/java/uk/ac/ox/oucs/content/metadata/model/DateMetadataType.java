package uk.ac.ox.oucs.content.metadata.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Colin Hebert
 */
public class DateMetadataType extends MetadataType<Date>
{
	private Date minimumDateTime;
	private Date maximumDateTime;
	private boolean dateOnly;
	private final DateTimeConverter converter = new DateTimeConverter();


	@Override
	public MetadataRenderer getRenderer()
	{
		return null;	//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public MetadataConverter<Date> getConverter()
	{
		return converter;
	}

	@Override
	public MetadataValidator<Date> getValidator()
	{
		return new DateMetadataValidator();
	}


	private final class DateMetadataValidator implements MetadataValidator<Date>
	{
		public boolean validate(Date object)
		{
			if (object == null)
				return isRequired();
			if (minimumDateTime != null && object.before(minimumDateTime))
				return false;
			if (maximumDateTime != null && object.after(maximumDateTime))
				return false;

			return true;
		}
	}

	private final class DateTimeConverter implements MetadataConverter<Date>
	{
		public String toString(Date object)
		{
			if (object == null)
				return null;
			return DateFormat.getDateInstance().format(object);
		}

		public Date toObject(String string)
		{
			if(string == null)
				return null;
			try
			{
				return DateFormat.getDateInstance().parse(string);
			}
			catch (ParseException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
