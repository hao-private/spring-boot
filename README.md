Debug:
1. mvnDebug spring-boot:run
2. use debug remote java application


Run:
mvn spring-boot:run


#create keystore
keytool -genkey -alias springbootexample -keyalg RSA -keystore src/main/resources/tomcat.keystore

#run spring boot with ssl
mvn spring-boot:run -Drun.profiles=https

