# onlygo [![Build Status](https://travis-ci.org/heptastique/onlygo.svg?branch=master)](https://travis-ci.org/heptastique/onlygo) [![codecov](https://codecov.io/gh/heptastique/onlygo/branch/master/graph/badge.svg)](https://codecov.io/gh/heptastique/onlygo)

## Setup

### Pré-requis
- Maven
- MySQL
- IntelliJ

### Installation
1. Vérifier l'installation de Maven en exécutant à la racine du projet
```
mvn install
```
2. Créer une base de données avec le nom `db_example` avec le port `3306` dans MySQL avec les identifiants suivants :
```
username: spring
password: password
```
3. Importer le projet dans IntelliJ en tant que projet Maven
4. Ajouter les clés API OpenWeather et AirAtmo dans `application.properties`

```
atmoApiKey=<cle>
weatherApiKey=<cle>
```

## Tests
Les tests peuvent être exécutés via IntelliJ ou avec
```
mvn test
```

## Documentation

La documentation des points d'accès fournis par ce serveur est accessible dans le dossier [docs](https://github.com/heptastique/onlygo/tree/master/docs)

## Front-end

Tout le code source est dans [onlygo-app](https://github.com/heptastique/onlygo-app)

## Livrables

Tous les livrables demandés sont dans le dossier [livrables](https://github.com/heptastique/onlygo/tree/master/livrables)
