pkill -f ./target/backend-0.0.1-SNAPSHOT.jar
sudo mvn package
nohup java -jar ./target/backend-0.0.1-SNAPSHOT.jar &
