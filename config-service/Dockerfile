FROM java:8-jre
MAINTAINER Sergio Jimenez <sergiojimenezfemenia90@gmail.com>

ADD ./target/config-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-service.jar"]

HEALTHCHECK --interval=5s --timeout=3s CMD curl --fail http://localhost:8888/ || exit 1

EXPOSE 8888