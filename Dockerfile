FROM eclipse-temurin:24-jre
WORKDIR /app
COPY moneymanager/target/moneymanager-0.0.1-SNAPSHOT.jar moneymanager-v1.0.jar
EXPOSE 8080

#ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar", "--server.port=$PORT"]

ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Entry point with proper port handling
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar moneymanager-v1.0.jar --server.port=${PORT:-8080}"]
