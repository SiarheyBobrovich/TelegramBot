FROM postgres:14.4-alpine
MAINTAINER Bobrovich.by

ADD /finance-service/ddl/*.sql /docker-entrypoint-initdb.d
ADD /user-service/ddl/*.sql /docker-entrypoint-initdb.d
ADD /user-service/dml/*.sql /docker-entrypoint-initdb.d