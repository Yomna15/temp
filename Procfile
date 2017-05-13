term web: java -jar target\dependency\jetty-runner.jar –port %PORT% target\*.war 

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>2.3</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals><goal>copy</goal></goals>
            <configuration>
                <artifactItems>
                    <artifactItem>
                        <groupId>org.eclipse.jetty</groupId>
                        <artifactId>jetty-runner</artifactId>
                        <version>9.2.7.v20150116</version>
                        <destFileName>jetty-runner.jar</destFileName>
                    </artifactItem>
                </artifactItems>
            </configuration>
        </execution>
    </executions>
</plugin>


https://nameless-citadel-22043.herokuapp.com/ | https://git.heroku.com/nameless-citadel-22043.git


https://git.heroku.com/nameless-citadel-22043.git
