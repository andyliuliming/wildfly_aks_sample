#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

export RESOURCE_GROUP_NAME=liuandyaksperftest
export AKS_CLUSTER_NAME=liuandyaksperftest
export ACR_NAME=liuandyacr
export AKS_ADMIN_NAME=aksperf
az group create --name liuandyaksperftest --location westus

az aks create --resource-group $RESOURCE_GROUP_NAME \
 --name $AKS_CLUSTER_NAME \
 --node-vm-size Standard_DS1_v2 \
 --node-count 1 \
 --admin-username $AKS_ADMIN_NAME \
 --kubernetes-version 1.11.4 --generate-ssh-keys

az aks get-credentials --resource-group $RESOURCE_GROUP_NAME --name $AKS_CLUSTER_NAME 


az acr create --resource-group $RESOURCE_GROUP_NAME --name $ACR_NAME --sku Basic
az acr login --name $ACR_NAME 

pushd $DIR/maven/webapp
  mvn package
popd

cp $DIR/maven/webapp/target/clustered-app.war $DIR/docker-images/

pushd $DIR/docker-images
  docker build --tag=$ACR_NAME.azurecr.io/samples/clustered-app .
  docker push $ACR_NAME.azurecr.io/samples/clustered-app
popd

