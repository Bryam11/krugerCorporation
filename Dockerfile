# Imagen base
FROM openjdk:11

# Copia el archivo .jar a la imagen
COPY ./target/ApiVaccine*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]