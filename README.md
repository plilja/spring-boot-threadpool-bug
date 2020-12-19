Shows a Spring boot bug.

To run:
```
mvn clean install
java -jar target/threadpool-0.0.1-SNAPSHOT.jar
# You should see 0 as value which indicates that thread pool is not used
# queuedThreadPoolSize is a gauge that shows the size of the custom http thread pool
curl http://localhost:8080/actuator/metrics/queuedThreadPoolSize | jq
```

Change Spring version in pom.xml and redo steps to see that this worked
in version 2.2.0.RELEASE.
