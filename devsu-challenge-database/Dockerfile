FROM postgres:14.3
RUN localedef -i es_EC -c -f UTF-8 -A /usr/share/locale/locale.alias es_EC.UTF-8
ENV LANG=es_EC.utf8 \
    TZ=America/Guayaquil \
    POSTGRES_DB=DEVSU \
    POSTGRES_USER=DEVSU \
    POSTGRES_PASSWORD=DEVSU
COPY ./BaseDatos.sql /docker-entrypoint-initdb.d/init.sql
EXPOSE 5432
