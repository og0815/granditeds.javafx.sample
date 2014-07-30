granditeds.javafx.sample
========================

This is a changed example of the data tutorial to verify the usage with JavaFX, Tomee and Hibernate.
At the moment the following things are not supported by GraniteDS in that specific environment:
- Lazy loading of entity collections
- Usage of multiple EnitityManagers on the server side.
- Also a very sluggish behaviour in a JavaFX List component when loading paged results has been discovered.

Changes to the original one at https://github.com/graniteds-tutorials/graniteds-tutorial-data
- Removed all server and client projects except client-javafx, server-model and server-ejb
- cleared the parent tree of pom's. Only one master pom is now used.
- created a tryout for now to show the failing lazy loading

# Running the example
1. In root call mvn clean install
2. In server-ejb call mvn tomee:run
3. In client-tryout call mvn jfx:run . You should end with a NPE
4. Also the scrolling behaviour can be seen by calling mvn jfx:run in client-javafx and scroll up and down.
