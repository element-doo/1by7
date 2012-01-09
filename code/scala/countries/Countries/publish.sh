#!/bin/bash

echo Will upload the classes, source and documentation to the ivy/m2 server
`dirname $0`/sbt.sh --no-jrebel "$@" publish
