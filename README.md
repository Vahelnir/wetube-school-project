# WeTube

## Introduction

School project where we have to create a Youtube/Netflix-like video streaming platform with JavaEE.

We have to ask the user if he wants to allow the platform to mine crypto-currencies on his browser. If the user accepts,
then he gets some more features.

This is not meant to be used as a real service though, and is in no way a finished, optimized nor secure state.

Developed with IntelliJ Idea IDE.

## Configuration

### Database

To edit the database configuration, edit: `src/main/resources/META-INF/persistence.xml` and edit:

- `javax.persistence.jdbc.driver`: the driver used, can be `org.mariadb.jdbc.Driver` or `com.mysql.jdbc.Driver`
- `javax.persistence.jdbc.url`: `jdbc:mariadb://` for **mariadb** and `jdbc:mysql://` for **mysql**

### SQL file with some data

Here are some data if you want to quickly see how the application react
`./data.sql`

Account:

- email: `admin@admin.fr`
- password: `134567890`

## Mining

For the mining-side of the project, we chose to use https://coinimp.com.

It is relatively simple to integrate their script into the application.

We limited the mining to only use a maximum of **50%** of the cpu (?).

On our application, with an ultrabook using a i7-8550u, an Intel UHD Graphics 620 and while decoding a video in 1080p,we
get **65** hashes per second.

- 1 Hash = 0.0000000963 MintMe (15/02/2022)
- 1 MintMe = 0.0065€

If we have 500 persons mining 65 hashes/s, then we get 0.000000312975€/s.

If every one of the 500 persons are watching a 15 minutes video, we get 0.0002816775€.
(and this is without taking into account what CoinImp is taxing...)

This is not profitable at all, and is a waste of power.

### Notes

Even tough we had to use web-mining for this project, but mining on the browser is not a great solution.

It is either slowing down the browser to the point of slowing the video-decoding, or not profitable.

And it is not ethical to mine on the user computer, even if they know that it is mining.

## Requirements

- **Tomcat 9**: can not use Tomcat 10 because Hibernate 5 with HikariCP does not work with Jakarta (or I don't know how
  to make it work), and we didn't want to use Hibernate 6 Beta
- **Java 17**: can probably still work with Java 8 or 11, though we have not tested with these versions 🤷

## Libraries used

- **javax.servlet-api**: API used with the tomcat implementation
- **cdi-api**: API used with the weld-servlet-shaded implementation
- **weld-servlet-shaded**: implementation of the CDI API
- **Hibernate**: used as a JPA implementation (hibernate-core & hibernate-hikaricp)
- **HikariCP**: used to provide pools to Hibernate
- **mysql-connector-java**: used to work with MySQL
- **mariadb-connector-java**: used to work with MariaDB
- **spring-security-web**: used to provide bcrypt

## Authors

Made by Romain FONTAINE and Valentin HAMAYON VOISIN.