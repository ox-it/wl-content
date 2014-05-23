Content Metadata
================

This set of projects supports the ability to define a metadata schema and then use this schema
to assign the metadata to files/folders in resources.

By default the metadata is stored in a site in a folder in the root of each site called metadata
and the file that is loaded is called metadata.json 

This is a sample config:

    [ {
        "metadataTypes" : [ { "@class" : "uk.ac.ox.oucs.content.metadata.model.BsgWeekMetadataType",
              "name" : "Start week",
              "id" : "bsg_start_week"
            },
            { "@class" : "uk.ac.ox.oucs.content.metadata.model.BsgWeekMetadataType",
              "name" : "End week",
              "id" : "bsg_end_week"
            },
            { "@class" : "uk.ac.ox.oucs.content.metadata.model.UserMetadataType",
              "name" : "Convener",
             "id" : "bsg_convener"
            }
        ],
        "expanded" : true,
        "name" : "Blavatnik school of government",
        "id" : "bsg",
        "@class" : "uk.ac.ox.oucs.content.metadata.model.GroupMetadataType"
      }
    ]