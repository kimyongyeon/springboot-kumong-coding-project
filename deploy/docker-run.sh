#!/bin/zsh
docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t springboot/kumong-coding-project
docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t springboot/kumong-coding-project
docker run -e "JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t springboot/kumong-coding-project