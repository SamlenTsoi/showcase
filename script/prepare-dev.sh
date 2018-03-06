#!/usr/bin/env bash

for i in `find ../ -type f -name 'application.*example'`
do
    cp -n $i `echo $i | sed -e 's/.example//'`
done