
FROM adoptopenjdk/openjdk13:jre

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /

ENTRYPOINT ["java", \
  "-server",\
  "-Dserver.shutdown=graceful",\
  "-Dspring.lifecycle.timeout-per-shutdown-phase=25s",\
  "-jar",\
  "/app.jar"\
]