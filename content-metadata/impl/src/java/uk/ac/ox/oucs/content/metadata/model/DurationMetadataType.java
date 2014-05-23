package uk.ac.ox.oucs.content.metadata.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Collections;
import java.util.Map;

/**
 * This supports the duration metadata type which was introduced for LOM support.
 *
 * @author Matthew Buckett
 */
public class DurationMetadataType extends MetadataType<Duration> {

	/**
	 * Allow the velocity context to easily get the Unit enum.
	 */
	public Duration.Unit[] getUnits() {
		return Duration.Unit.values();
	}

	@Override
	public MetadataRenderer getRenderer() {
		return new MetadataRenderer() {
			@Override
			public String getMetadataTypeEditTemplate() {
				return null;
			}

			@Override
			public String getMetadataTypeDisplayTemplate() {
				return null;
			}

			@Override
			public String getMetadataValueEditTemplate() {
				return "meta_edit_duration";
			}

			@Override
			public String getMetadataValueDisplayTemplate() {
				return "meta_display_string";
			}
		};
	}

	@Override
	public MetadataConverter<Duration> getConverter() {
		return new MetadataConverter<Duration>() {
			@Override
			public String toString(Duration metaValue) {
				if (metaValue != null) {
					StringBuilder builder = new StringBuilder();
					builder.append(nullToEmpty(metaValue.getFirstCount()));
					builder.append("-");
					builder.append(nullToEmpty(metaValue.getFirstUnit()));
					builder.append("-");
					builder.append(nullToEmpty(metaValue.getSecondCount()));
					builder.append("-");
					builder.append(nullToEmpty(metaValue.getSecondUnit()));
					return builder.toString();
				}
				return null;
			}

			@Override
			public Duration fromString(String stringValue) {
				if (stringValue != null && !stringValue.isEmpty()) {
					String [] values = StringUtils.splitPreserveAllTokens(stringValue, "-");
					if (values.length == 4) {
						Duration duration = new Duration();
						duration.setFirstCount(NumberUtils.toInt(values[0]));
						duration.setFirstUnit(Duration.Unit.parse(values[1]));
						duration.setSecondCount(NumberUtils.toInt(values[2]));
						duration.setSecondUnit(Duration.Unit.parse(values[3]));
						return duration;
					}
				}
				return null;
			}

			public Map<String, ?> toProperties(Duration metadataValue)
			{
				String stringValue = toString(metadataValue);
				return Collections.singletonMap(getUniqueName(), (stringValue == null || metadataValue.isEmpty())?null: stringValue);
			}

			public Duration fromProperties(Map<String, ?> properties)
			{
				return fromString((String) properties.get(getUniqueName()));
			}

			public Duration fromHttpForm(Map<String, ?> parameters, String parameterSuffix)
			{
				Duration duration = new Duration();
				duration.setFirstCount(toInteger(parameters.get(getUniqueName() + "_first_count"+ parameterSuffix)));
				duration.setFirstUnit(Duration.Unit.parse(parameters.get(getUniqueName() + "_first_unit" + parameterSuffix)));
				duration.setSecondCount(toInteger(parameters.get(getUniqueName() + "_second_count" + parameterSuffix)));
				duration.setSecondUnit(Duration.Unit.parse(parameters.get(getUniqueName() + "_second_unit" + parameterSuffix)));
				return duration;
			}
		};
	}

	/**
	 * @param obj The Object.
	 * @return return the string version of the object or an empty string if it's null.
	 */
	private String nullToEmpty(Object obj) {
		return (obj == null)? "" : obj.toString();
	}

	private Integer toInteger(Object obj) {
		if (obj != null) {
			try {
				return Integer.parseInt(obj.toString());
			} catch (NumberFormatException nfe) {
				// Ignore
			}
		}
		return null;
	}

	@Override
	public MetadataValidator<Duration> getValidator() {
		return new MetadataValidator<Duration>() {
			@Override
			public boolean validate(Duration metadataValue) {
				// TODO
				return true;
			}
		};
	}
}
