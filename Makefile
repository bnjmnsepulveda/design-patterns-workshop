
# run app
run:
	mvn package
	mvn exec:java

init-db:
	docker run -d --rm --name postgres -e POSTGRES_PASSWORD=witi -p 5432:5432 postgres
