
FROM java:8
VOLUME /tmp
ADD ./target/movie-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongocontainer2/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]