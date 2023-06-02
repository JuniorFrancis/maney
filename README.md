# maney - managed money 

### Description

<p>This API have the ideia to manage your maney informations without sensitive data from you like CPF, Card Numbers with bank information. You only need the last 4 digits of your card if you used it, and the value of your spent.</p>
<p> You can also input how much you received in this determined month. If you prefer not to include it, the api will have the same operation. <br>
  With all these informations the API tell you if you spent more then received, tell you what your biggest expense was and more. </p>


### Tecnologies

Gradle: 7.6.1
Java: 17
Spring Boot: 3.0.6

### Instructions 

I'm using MySQL to database, upping a local database with wamp.

<span>There no have secrects to run the application. After upping the DB run the following commands: <br> 
  
  ``` ./gradlew clean build ```
  and often
  ``` ./gradlew bootRun ```
