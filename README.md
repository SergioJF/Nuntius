
[![Build Status](https://travis-ci.org/SergioJF/TFG.svg?branch=master)](https://travis-ci.org/SergioJF/TFG)
[![codecov](https://codecov.io/gh/SergioJF/TFG/branch/master/graph/badge.svg)](https://codecov.io/gh/SergioJF/TFG)

# TFG - NUNTIUS

## Trello 

[Trello](https://trello.com/b/vvEc7eQt/sergio-jimenez-tfg-16-17)

## Gitflow

## SetUp

Para exportar las variables de entorno con las contraseñas, necesarias para el servidor de configuración, creamos dentro del directoria /env el script setup.sh.

```
export CONFIG_SERVICE_PASSWORD=admin
```

Necesitaremos ejecutarlo antes de lanzar el docker-compose

```
$ . ./setups.sh
```

## MongoDB
Definimos el script para iniciar la base de datos mongodb.

```bash
#!/usr/bin/env bash

#!/bin/bash
if test -z "$MONGODB_PASSWORD"; then
    echo "MONGODB_PASSWORD env variable not defined"
    exit 1
fi

auth="-u user -p $MONGODB_PASSWORD"

# MONGODB USER CREATION
(
echo "setup mongodb auth"
create_user="if (!db.getUser('user')) { db.createUser({ user: 'user', pwd: '$MONGODB_PASSWORD', roles: [ {role:'readWrite', db:'nuntius'} ]}) }"
until mongo nuntius --eval "$create_user" || mongo nuntius $auth --eval "$create_user"; do sleep 5; done
killall mongod
sleep 1
killall -9 mongod
) &


echo "start mongodb without auth"
chown -R mongodb /data/db
gosu mongodb mongod "$@"

echo "restarting with auth on"
sleep 5
exec gosu mongodb mongod --auth "$@"
```

## Travis CI 

Login con docker hay que setear 3 variables de entorno

## Codecov

## Docker

## Docker Hub

## Despliegues: AWS - ECS