项目打包后包含以下2个目录:
./lib
./project.jar
其中,project.jar是启动jar包;lib目录是项目依赖所有jar包

启动语句:
java -jar -Dloader.path="./lib/" project.jar
或者将lib假如classpath中;可以直接启动
java -jar project.jar