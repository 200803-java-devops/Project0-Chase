FROM postgres
ENV POSTGRES_USER postgres-user
ENV POSTGRES_PASSWORD 9876
ENV POSTGRES_DB journaldb
ADD *.sql /docker-entry-initdb.d
EXPOSE 5432