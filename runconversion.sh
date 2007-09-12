#!/bin/sh
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/commons-dbcp/commons-dbcp/1.2.2/commons-dbcp-1.2.2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/commons-pool/commons-pool/1.3/commons-pool-1.3.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/mysql/mysql-connector-java/3.1.11/mysql-connector-java-3.1.11.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/org/sakaiproject/sakai-util-api/M2/sakai-util-api-M2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/org/sakaiproject/sakai-entity-api/M2/sakai-entity-api-M2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/org/sakaiproject/sakai-entity-util/M2/sakai-entity-util-M2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/org/sakaiproject/sakai-content-api/M2/sakai-content-api-M2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/org/sakaiproject/sakai-content-impl/M2/sakai-content-impl-M2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/org/sakaiproject/sakai-util-log/M2/sakai-util-log-M2.jar"
CLASSPATH="$CLASSPATH:$HOME/.m2/repository/log4j/log4j/1.2.9/log4j-1.2.9.jar"

java $JAVA_OPTS  \
      -classpath "$CLASSPATH" \
      org.sakaiproject.content.impl.serialize.impl.conversion.UpgradeSchema "$@" 
