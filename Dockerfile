FROM ubuntu:latest
LABEL authors="Ignacio"

ENTRYPOINT ["top", "-b"]