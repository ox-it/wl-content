package uk.ac.ox.oucs.content.metadata.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.ac.ox.oucs.content.metadata.model.DateMetadataType;
import uk.ac.ox.oucs.content.metadata.model.GroupMetadataType;
import uk.ac.ox.oucs.content.metadata.model.MetadataType;
import uk.ac.ox.oucs.content.metadata.model.StringMetadataType;

/**
 * Hardcoded DublinCore metadataModel to facilitate transition to the new metadata system.
 *
 * This system should NOT be used except for DublinCore
 *
 * @author Colin Hebert
 */
public final class MetadataServiceDublinCore implements MetadataService
{
	private final List<MetadataType> dublinCoreMeta;

	public MetadataServiceDublinCore()
	{
		GroupMetadataType dcMetadata = new GroupMetadataType();
		dcMetadata.setName("Dublin core");
		dcMetadata.setUniqueName("dublin_core");
		List<MetadataType<?>> subTags = new ArrayList<MetadataType<?>>();
		dcMetadata.setMetadataTypes(subTags);

		StringMetadataType alternateTitle = new StringMetadataType();
		alternateTitle.setName("Alternate title");
		alternateTitle.setUniqueName("dublin_core_alternate_title");
		subTags.add(alternateTitle);

		StringMetadataType creator = new StringMetadataType();
		creator.setName("Creator");
		creator.setUniqueName("dublin_core_creator");
		subTags.add(creator);

		StringMetadataType publisher = new StringMetadataType();
		publisher.setName("Publisher");
		publisher.setUniqueName("dublin_core_publisher");
		subTags.add(publisher);

		StringMetadataType subjectKeywords = new StringMetadataType();
		subjectKeywords.setName("Subject and keywords");
		subjectKeywords.setUniqueName("dublin_core_publisher_subject_and_keywords");
		subjectKeywords.setLongText(true);
		subTags.add(subjectKeywords);

		DateMetadataType dateCreated = new DateMetadataType();
		dateCreated.setName("Date created");
		dateCreated.setUniqueName("dublin_core_date_created");
		subTags.add(dateCreated);

		DateMetadataType dateIssued = new DateMetadataType();
		dateIssued.setName("Date issued");
		dateIssued.setUniqueName("dublin_core_date_issued");
		subTags.add(dateIssued);

		StringMetadataType abstractText = new StringMetadataType();
		abstractText.setName("Abstract");
		abstractText.setUniqueName("dublin_core_abstract");
		abstractText.setLongText(true);
		subTags.add(abstractText);

		StringMetadataType contributor = new StringMetadataType();
		contributor.setName("Contributor");
		contributor.setUniqueName("dublin_core_contributor");
		contributor.setLongText(true);
		subTags.add(contributor);

		StringMetadataType audience = new StringMetadataType();
		audience.setName("Audience");
		audience.setUniqueName("dublin_core_audience");
		subTags.add(audience);

		StringMetadataType audienceEducationLevel = new StringMetadataType();
		audienceEducationLevel.setName("Audience Education Level");
		audienceEducationLevel.setUniqueName("dublin_core_audience_education_level");
		audienceEducationLevel.setLongText(true);
		subTags.add(audienceEducationLevel);

		dublinCoreMeta = Collections.<MetadataType>singletonList(dcMetadata);
	}

	public List<MetadataType> getMetadataAvailable(String resourceType)
	{
		return dublinCoreMeta;
	}

	public List<MetadataType> getMetadataAvailable(String siteId, String resourceType)
	{
		return dublinCoreMeta;
	}
}
