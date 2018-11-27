az group create --name liuandyaksperftest --location westus

az aks create --resource-group liuandyaksperftest --name liuandyaksperftest --node-vm-size Standard_DS2_v2 --node-count 1 --admin-username askperf --kubernetes-version 1.11.4 --generate-ssh-keys

az aks get-credentials --resource-group liuandyaksperftest --name liuandyaksperftest

