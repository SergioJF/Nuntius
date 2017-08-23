FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/client-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/client-service.jar"]

EXPOSE 9100