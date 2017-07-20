FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/gateway-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service.jar"]

EXPOSE 4000