FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 9876
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432