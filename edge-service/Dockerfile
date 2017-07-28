FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/edge-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/edge-service.jar"]

EXPOSE 4444