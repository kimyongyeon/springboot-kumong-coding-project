#!/bin/zsh

# 의존성파일 폴더로 이동 후 build
mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
docker build --build-arg DEPENDENCY=build/dependency -t springboot/kumong-coding-project .
docker build -t springboot/kumong-coding-project .

# 기본 build
docker build --build-arg JAR_FILE=build/libs/\*.jar -t springboot/kumong-coding-project .COPY
