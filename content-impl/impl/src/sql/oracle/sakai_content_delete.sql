-----------------------------------------------------------------------------
-- CONTENT_RESOURCE_DELETE
-- TODO: add CONTENT_RESOURCE_BODY_BINARY_DELETE table if required
-----------------------------------------------------------------------------

CREATE TABLE CONTENT_RESOURCE_DELETE
(
    RESOURCE_ID VARCHAR2 (255) NOT NULL,
    RESOURCE_UUID VARCHAR2 (36),
	IN_COLLECTION VARCHAR2 (255),
	CONTEXT VARCHAR2 (99),
	FILE_PATH VARCHAR2 (128),
	FILE_SIZE NUMBER(18),
	DELETE_DATE DATE,
	DELETE_USERID VARCHAR2 (36),
    XML LONG,
    BINARY_ENTITY BLOB
);

CREATE UNIQUE INDEX CONTENT_RESOURCE_UUID_DELETE_I ON CONTENT_RESOURCE_DELETE
(
	RESOURCE_UUID
);

CREATE INDEX CONTENT_RESOURCE_DELETE_INDEX ON CONTENT_RESOURCE_DELETE
(
	RESOURCE_ID
);
