FROM maven:3.6.0-jdk-8

# run commands inside this repository
WORKDIR /usr/src/app

# Create folder to share JDK
RUN mkdir -p /usr/share/jdk

COPY . /usr/src/app

# Expose the port the app runs in
EXPOSE 9090

#CMD ["mvn","spring-boot:run"]
CMD mvn spring-boot:run


