
# build and deploy inventory
oc new-app --name inventory-database -e POSTGRESQL_USER=inventory -e POSTGRESQL_PASSWORD=mysecretpassword -e POSTGRESQL_DATABASE=inventory registry.redhat.io/rhel9/postgresql-15
mvn clean compile package -DskipTests -f inventory-service

# build and deploy catalog
oc new-app -e POSTGRESQL_USER=catalog -e POSTGRESQL_PASSWORD=mysecretpassword -e POSTGRESQL_DATABASE=catalog openshift/postgresql:latest --name=catalog-database
mvn clean install -Ddekorate.deploy=true -DskipTests -f catalog-service

# build and deploy cart
oc new-app --as-deployment-config quay.io/openshiftlabs/ccn-infinispan:12.0.0.Final-1 --name=datagrid-service -e USER=user -e PASS=pass
mvn clean package -DskipTests -f cart-service

# build and deploy order
oc new-app --as-deployment-config --docker-image quay.io/openshiftlabs/ccn-mongo:4.0 --name=order-database
mvn clean package -DskipTests -f order-service


# build and deploy coolstore-ui
cd coolstore-ui
nvm use `cat .nvmrc`
npm ci
npm run nodeshift && oc expose svc/coolstore-ui
cd ..

# build and deploy payment
mvn clean package -Pnative -DskipTests -f payment-service

# build and deploy simulator
mvn clean package -DskipTests -f simulator