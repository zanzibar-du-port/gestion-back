FROM openjdk:12
ADD target/template-back-ecommerce.jar template-back-ecommerce.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "template-back-ecommerce.jar"]