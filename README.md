# Deploy to remote server
./mvnw clean package
scp ./target/demo-0.0.1-SNAPSHOT.jar root@159.223.111.103:/root/artifact/
ssh root@159.223.111.103 "nohup java -jar ./artifact/demo-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &"