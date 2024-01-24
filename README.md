Overview
<br>
This project is a Java-based implementation of a custom hash table, featuring various components such as collision handling, iterators, and custom exceptions. It is designed to provide an illustrative example of data structures and algorithms in Java.


Main Application
AppMain.java: The entry point of the application. It likely initializes and demonstrates the usage of other components in the project.
Hash Table and Related Classes
ArrayHashTable.java: Implements a hash table using arrays. This class probably contains methods for adding, removing, and retrieving elements based on keys.
HashTable.java: An abstract representation of a hash table, likely providing a foundation for specific hash table implementations like ArrayHashTable.

Collision Handling
CollisionHandler.java: An interface or abstract class for handling collisions in the hash table.
LinearCollisionHandler.java: Implements linear probing method for collision resolution.
QuadraticCollisionHandler.java: Implements quadratic probing method for collision resolution.

Utilities and Iterators
KeyIterator.java: Provides an iterator for traversing through the keys in the hash table.

Custom Exceptions
ElementNotFoundException.java: Exception thrown when an element is not found in the hash table.
EmptyCollectionException.java: Exception thrown when an operation is attempted on an empty collection.

Database Simulation
CredentialDatabase.java: Simulates a database, possibly using the hash table for storing and retrieving credentials.

Testing
PublicArrayHashTableTest.java: Contains test cases for ArrayHashTable, likely using JUnit framework.

Dependencies
JUnit (junit-4.12.jar): For writing and executing tests.
Hamcrest (hamcrest-core-1.3.jar): Provides matchers for testing assertions in JUnit.
