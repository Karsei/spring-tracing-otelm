# spring-tracing-otelm

opentelemetry 라이브러리를 이용해서 tracing 정보를 받아서 사용할 수 있는지 확인해보기 위한 sample web 애플리케이션

## Build

```bash
# Build
$ gradlew clean build

# containerd
$ nerdctl build -t spring-tracing .
# dockerd
$ docker build -t spring-tracing . 
```
