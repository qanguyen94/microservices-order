FROM eclipse-temurin:11

WORKDIR /my-app

COPY ./target/order-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "order-0.0.1-SNAPSHOT.jar"]
