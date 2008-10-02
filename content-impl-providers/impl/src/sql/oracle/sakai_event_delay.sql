-----------------------------------------------------------------------------
-- SAKAI_EVENT
-----------------------------------------------------------------------------

CREATE TABLE SAKAI_EVENT_DELAY
(
	EVENT_DELAY_ID NUMBER,
	EVENT VARCHAR2 (32),
	RESOURCE VARCHAR2 (255),
	USER_ID VARCHAR2 (99),
	EVENT_CODE NUMBER (1),
	PRIORITY NUMBER (1)
);

CREATE UNIQUE INDEX SAKAI_EVENT_DELAY_INDEX ON SAKAI_EVENT
(
	EVENT_DELAY_ID
);

CREATE SEQUENCE SAKAI_EVENT_DELAY_SEQ;