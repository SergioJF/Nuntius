FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/web-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/web-service.jar"]

EXPOSE 9999