FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/ui-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ui-service.jar"]

EXPOSE 8080