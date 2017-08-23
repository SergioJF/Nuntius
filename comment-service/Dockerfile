FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/comment-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/comment-service.jar"]

EXPOSE 9000