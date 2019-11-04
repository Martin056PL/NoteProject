FROM openjdk:12.0.2-jdk
ADD target/notetask-1.0.jar .
ADD dockerscript/wait .
RUN chmod +x /wait
CMD /wait && java -jar notetask-1.0.jar --spring.profiles.active=container
EXPOSE 8888
LABEL profile="container"