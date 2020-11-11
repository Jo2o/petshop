web: java -Dserver.port=$PORT $JAVA_OPTS -jar target/petshop*.jar
release: java -jar target/dependency/liquibase.jar --changeLogFile=db/changelog/db.changelog-master.xml --url=$JDBC_DATABASE_URL --classpath=target/dependency/postgres.jar update
