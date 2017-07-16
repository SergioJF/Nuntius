FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/eureka-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka-service.jar"]

EXPOSE 8761