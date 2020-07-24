token = "paste discord token here"

all: build run

build:
	mvn package -q

run:
	java -jar target/Kali-1.jar $(token)
