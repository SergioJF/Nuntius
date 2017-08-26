






[![Build Status](https://travis-ci.org/SergioJF/Nuntius.svg?branch=master)](https://travis-ci.org/SergioJF/Nuntius)
[![codecov.io](https://codecov.io/github/SergioJF/Nuntius/coverage.svg?branch=master)](https://codecov.io/github/SergioJF/Nuntius?branch=master)

# TFG - NUNTIUS

## ¿Microservicios?

> In short, the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.
 >  --James Lewis and Martin Fowler


## Arquitectura de  Microservicios

Este tipo de arquitectuar surgen para dar solucion a problemas inherentes a los sistemas monolíticos. Estas son algunas de las ventajas que aportan:

- Servicios pequeños e independientes (Principio de responsabilidad única).
- Unidades de despliegue pequeñas.
- Reduccion de tiempo de desarrollo.
- Agilidad en "Hot fixes".
- Riqueza tecnológica.
- Fácil escalado horizontal.
- Automatización de despliegues.




## Spring Cloud Netflix

![MS-Arc](img/MS-Arc.png?style=centerme)

La mayoría de los componentes de la arquitectura fueron originalmente desarrollados por Netflix, el conocido site de streaming online, el cual recibe el mayor tráfico de red de bajada de todo EEUU. La arquitectura de Netflix está compuesta por mas de 500 microservicios, y cuenta con más de 50 millones de suscriptores que realizan unas 2.000 millones de peticiones al día.

A continuación se analizarán una selección de los componentes más importantes de dicha arquitectura.

### Eureka

#### ¿Qué es?

Eureka es un servidor para el registro y localización de microserviceios, balance de carga y tolerancia a fallos. La funciión de Eureka es registrar las diferentes instancias de microservicios existentes, su localización, estado, metadatos, ... etc.


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

```java
@Service
public class UserServiceImpl implements UserService{

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    @Override
    public void create(User user) {
        User existing = repository.findOne(user.getUsername());

        Assert.isNull(existing, "User already exists: " + user.getUsername());

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("New user has been created: {}", user.getUsername());
    }
}
```