Work on the assignment given from here:
https://gist.github.com/sylvain-charton/f91bab7958daf88cc6845cddb92434d4

I've bootstrapped the project from https://start.spring.io/ 
- Java 17 
- Build as Jar 
- Maven Dependency Management

I've added https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1 as json mapping library.
I've added https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30 - 
so I could create clean of boilerplate code Plain Old Java Objects.

I've used a personally developed tool: https://programtom.com/dev/product/json-model-extractor-quarkus-vaadin-web-app/ 
That parses JSON and outputs models as I like them.

The Assignment is 
- Grab Data from remote data source
- Do some transformations with it
- Pass it to the Front-End

For the purpose of the showcase - I've implemented simple ThymeLeaf template - HTML display.