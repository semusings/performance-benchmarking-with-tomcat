#!/usr/bin/env bash
read -p "Service Endpoint: "
export SERVICE_API_ENDPOINT=$REPLY
cd cicd/load-testing/artillery-load-testing && \
    yarn install && \
    ./node_modules/.bin/artillery run script.yml