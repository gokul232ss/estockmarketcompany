FROM openjdk:11
EXPOSE 8081
ADD target/estockmarketcompany_local.jar estockmarketcompany_local.jar
ENTRYPOINT ["java", "-jar", "estockmarketcompany_local.jar"]