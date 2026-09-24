# Medical Project

Application web de gestion d'un cabinet medical construite avec une architecture microservices Spring Boot. Le projet propose un tableau de bord centralise pour piloter les patients, les medecins et les rendez-vous depuis une interface unique. Il illustre la conception d'un systeme distribue avec separation des responsabilites, communication inter-services, decouverte dynamique et persistance des donnees. L'objectif est de fournir une base evolutive pour digitaliser les operations quotidiennes d'un cabinet medical tout en gardant une architecture maintenable.

## Description du projet

Medical Project simule le fonctionnement d'un cabinet medical moderne : les utilisateurs peuvent consulter et creer des fiches patients, gerer les medecins et planifier les rendez-vous. L'interface communique avec une API Gateway, qui distribue les requetes vers les microservices concernes. Eureka assure la decouverte des services, Spring Cloud Config centralise la configuration et MySQL stocke les donnees metier. Cette organisation permet de faire evoluer chaque service independamment et de preparer l'application a une mise en production progressive.

## Fonctionnalites

- Consultation et creation de patients
- Gestion des medecins
- Creation et consultation des rendez-vous
- Tableau de bord web centralise
- Communication entre services avec WebClient et OpenFeign
- Decouverte des services avec Eureka
- Configuration centralisee avec Spring Cloud Config
- Persistance des donnees avec MySQL et Spring Data JPA

## Architecture

```text
                         +----------------------+
                         |   Interface service  |
                         |      Port 8083       |
                         +----------+-----------+
                                    |
                         +----------v-----------+
                         |     API Gateway      |
                         |      Port 8080       |
                         +----------+-----------+
                                    |
          +-------------------------+-------------------------+
          |                         |                         |
 +--------v--------+       +--------v--------+       +--------v--------+
 | Patient service |       |  RDV service    |       | Eureka / Config |
 |    Port 8081    |       |    Port 8082    |       | 8761 / 8888     |
 +-----------------+       +-----------------+       +-----------------+
```

## Services

| Module | Role | Port |
| --- | --- | ---: |
| `config-server` | Configuration centralisee | `8888` |
| `eureka-server` | Discovery des services | `8761` |
| `api-gateway` | Point d'entree des APIs | `8080` |
| `patient-service` | Gestion des patients | `8081` |
| `rdv-service` | Gestion des medecins et rendez-vous | `8082` |
| `interface-service` | Dashboard web | `8083` |
| `medical-model` | Entites partagees | - |

## Technologies

- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Config
- Netflix Eureka
- Spring Data JPA
- OpenFeign et WebClient
- MySQL
- Maven
- Thymeleaf

## Prerequis

- JDK 17 ou version ulterieure
- Maven 3.8+
- MySQL 8+
- Un depot de configuration accessible par `config-server`

## Configuration MySQL

Creer une base de donnees nommee `medicaldb`, puis verifier les identifiants dans les fichiers `application.properties` des services `patient-service`, `rdv-service` et `interface-service`.

```sql
CREATE DATABASE medicaldb;
```

Pour un environnement partage ou de production, remplacer les identifiants de demonstration par des variables d'environnement ou une configuration externe.

## Lancement

Depuis la racine du projet :

```bash
mvn clean install
```

Demarrer les modules dans cet ordre :

1. `config-server`
2. `eureka-server`
3. `patient-service` et `rdv-service`
4. `api-gateway`
5. `interface-service`

Le dashboard est ensuite disponible sur `http://localhost:8083/dashboard`.

## Structure

```text
medical-project-initializr/
├── api-gateway/
├── config-server/
├── eureka-server/
├── interface-service/
├── medical-model/
├── patient-service/
├── rdv-service/
└── pom.xml
```

## Auteur

Othmane Al Amrani - [GitHub](https://github.com/othmanealpro-crypto)
