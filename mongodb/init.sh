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