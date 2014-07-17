testng-system-out-printer
=========================

A Java TestNG Listener that prints test method names, millisecond timings, and outcomes to System.out as the tests are completed.


## License

This project is licensed under the Apache License Version 2.0 - for more details please see the LICENSE file.


## Configuration

To make TestNG use the Listener put something like the following in the configuration for your build system (the below is for a Maven pom):

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.17</version>
        <configuration>
            <properties>
                <property>
                    <name>listener</name>
                    <value>com.little_fluffy_clouds.testng.SystemOutTestPrinter</value>
                </property>
            </properties>
        </configuration>
    </plugin>
