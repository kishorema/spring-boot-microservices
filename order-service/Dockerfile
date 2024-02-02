FROM openjdk:17

COPY target/*.jar app.jar

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n","-jar","/app.jar"]
