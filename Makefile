
DB_NAME=db-workshop

build-app:
	mvn clean
	mvn package

run:
	mvn exec:java -Dmaven.test.skip=true

build-and-run: build-app run

start-db:
	docker build -t $(DB_NAME) .
	docker run -d --rm --name $(DB_NAME) -p 5432:5432 $(DB_NAME)
	docker ps

stop-db:
	docker stop $(DB_NAME)
	docker ps -a

