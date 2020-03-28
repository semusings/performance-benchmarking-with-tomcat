export KUBE_NAMESPACE := bhuwanupadhyay
export DEPLOYMENT := benchmark-elastic-apm
build:
	cd springboot-order-crud-service && mvn clean package -DskipTests
image:
	cp -R springboot-order-crud-service/target/springboot-order-crud-service.war cicd/docker
	docker-compose build
	rm -rf cicd/docker/springboot-order-crud-service.war
push: image
	docker-compose push
dockerAll: build image
up:
	docker-compose up
deploy:
	@read -p "Kube Api Endpoint: " endpoint; \
	helm upgrade \
        --install \
        --force \
        --set image.pullPolicy=Always \
        --set ingress.hosts[0]=api.${DEPLOYMENT}.$${endpoint} \
        --set kibana.ingress.hosts[0]=kibana.${DEPLOYMENT}.$${endpoint} \
        --namespace=${KUBE_NAMESPACE} \
        ${DEPLOYMENT}  \
        ./cicd/helm/${DEPLOYMENT}
destory:
	helm delete --purge ${DEPLOYMENT}
logs:
	cicd/scripts/kube-utils.sh logs ${KUBE_NAMESPACE} ${DEPLOYMENT}
logById:
	kubectl logs -f $(ID) --namespace ${KUBE_NAMESPACE}
pods:
	kubectl get pods --namespace ${KUBE_NAMESPACE}
describe:
	cicd/scripts/kube-utils.sh describe ${KUBE_NAMESPACE} ${DEPLOYMENT}
test:
	cicd/scripts/artillery-load-testing.sh
