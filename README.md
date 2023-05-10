# Tower of Hanoi Problem

This porject creates a flutter app to animate the Tower of Hanoi moves step-by-step. 


## Run and build API

Go to api directory with ``cd api``.

Test by running ``mvn test``.

Build apk by running ``./mvnw clean package``.

Copy the build .jar file to remote rerver by running ``scp ./target/demo-0.0.1-SNAPSHOT.jar root@<server_ip>:/root/artifact/``.

Run .jar within server as background process by running ``ssh root@<server_ip> "nohup java -jar ./artifact/demo-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &"``

## Run UI

Go to ui directory with ``cd ui``.

Make sure the work environment is setup correcty by running ``flutter doctor``.

Run app with ``flutter run``.
