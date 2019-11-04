FROM openjdk:12.0.2-jdk
ADD target/notetask-0.0.1.jar .
ADD dockerscript/wait .
RUN chmod +x /wait
CMD /wait && java -jar notetask-0.0.1.jar --spring.profiles.active=container
EXPOSE 8888
LABEL profile="container"