FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 8761