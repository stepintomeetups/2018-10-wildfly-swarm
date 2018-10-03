# Step Into Meetup - Wildfly Swarm Demo alkalmazás

A demo alkalmazás 5 szolgáltatást tartalmaz:
* host-service - Gazdák kezelése
* guest-service - Vendégek kezelése
* story-service - Történetszálak kezelése
* world-service - Világok kezelése
* dashboard-service - Kezelőpanel szolgáltatás

# Build
A főkönyvtárban ```mvn clean install``` parancs kiadása.

# Indítás
A szolgáltatások futtatásához szükséges egy PostgreSQL adatbázis amelyet a ``docker`` nevű mappában lévő ```docker-compose.yml```
fájlal is létrehozhat.  
Szükséges még egy Consul szerver ahová a szolgáltatsok lesznek beregisztrálva, ez is benne található a ```docker-compose.yml``` fájlban.  
A ```docker-compose up``` parancs kiadásával tudja elindítani.  
  
A szolgáltatások egyesével elindíthatóak a ```java -jar target/xy-service-swarm.jar``` állománnyal.  
Ahhoz, hogy a portokat eltolhassuk használjuk a ```-Dswarm.port.offset=szám``` kapcsolót

Ha alapjáraton nem szeretnénk, hogy az alkalmazásunk több mint 1 GB memóriát égessen el akkor használjuk a -Xmx256m kapcsolót, hogy csökkentsük a maximális memóriáját.  

Példa:  
A főkönyvtárban állva  
```java -jar host-service/target/host-service-swarm.jar -Dswarm.port.offset=0```  
```java -jar world-service/target/world-service-swarm.jar -Dswarm.port.offset=1```  
```java -jar story-service/target/story-service-swarm.jar -Dswarm.port.offset=2```  
```java -jar guest-service/target/guest-service-swarm.jar -Dswarm.port.offset=3```   
```java -jar dashboard-service/target/dashboard-service-swarm.jar -Dswarm.port.offset=4```  
Ha nem adjuk nekik át a kapcsolót akkor a ```project-defaults.yml``` fájlban lévő ```pom.xml```-ből feloldott értékű porton fog futni.

Ezek után a szolgáltatások különböző végpontokon hívhatóak.  
Swagger dokumentációk a következő linkeken érhetőek el: ```http://localhost:port/rest/swagger.json```  
Ott találhatóak a hívható végpontok.


# Docker-ban való futtatás
Ha szeretnék minden szolgáltatást **Docker**-ben futtatni akkor a ```demo_docker``` nevű mappában kell kiadnunk
a ```docker-compose up```  parancsot amellyel utána a ```demo.localhost``` URL-en érjük el a szolgáltatásokat, illetve a Swagger dokumentációt is.
