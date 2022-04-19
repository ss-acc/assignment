# First stage of the build will use maven 3.8.1 parent image
FROM maven:3.8.1-jdk-11 AS build

# Creating local directory
RUN mkdir /usr/src/project

# Copying all source codes to project directory
COPY . /usr/src/project

# Making the project directory as the working directory
WORKDIR /usr/src/project

# Packaging the application code
RUN mvn clean package

# Second stage of the build will use adoptopenjdk 11 image
FROM adoptopenjdk/openjdk11:latest

# Copying only the artifacts from the first stage and discarding the rest
COPY --from=build /usr/src/project/target/*.jar /checkout.jar

# Setting the startup command to execute the jar
CMD ["java","-jar","/checkout.jar"]


