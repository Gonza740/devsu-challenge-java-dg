FROM gradle:8.10.0-jdk17

ENV LANG=C.UTF-8 \
    TZ=America/Guayaquil

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime

WORKDIR /api

COPY . /api
RUN gradle build

EXPOSE 8080

ENTRYPOINT ["java", "-Ddisable-axoniq-console-message=true", "-jar", "./build/libs/api.jar"]
