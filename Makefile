#git clone https://github.com/wangsui1314/family-school.git
#mvn clean package
#打包结果  /root/jjxy/family-school/target/family-school.war
all:
	git pull https://github.com/wangsui1314/family-school.git master
	rm -rf ./target
	mvn clean package
	docker rm -f familyschool
	docker rmi familyschool
	docker build -t familyschool .
	docker run -p 7070:8080  --name familyschool  -d familyschool
