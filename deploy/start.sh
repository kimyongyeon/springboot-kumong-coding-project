#!/bin/zsh
docker build -t springboot/kumong-coding-project .
docker run -p 8080:8080 springboot/kumong-coding-project