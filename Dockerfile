FROM library/postgres
ENV POSTGRES_USER witi
ENV POSTGRES_PASSWORD witi
ENV POSTGRES_DB witi
COPY db.sql /docker-entrypoint-initdb.d/