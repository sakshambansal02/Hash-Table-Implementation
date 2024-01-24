Overview
<br>
This project is a Java-based implementation of a custom hash table, featuring various components such as collision handling, iterators, and custom exceptions. It is designed to provide an illustrative example of data structures and algorithms in Java.
<br>
<br>

Main Application
<br>
AppMain.java: The entry point of the application. It likely initializes and demonstrates the usage of other components in the project.
<br>
Hash Table and Related Classes
<br>
ArrayHashTable.java: Implements a hash table using arrays. This class probably contains methods for adding, removing, and retrieving elements based on keys.
<br>
HashTable.java: An abstract representation of a hash table, likely providing a foundation for specific hash table implementations like ArrayHashTable.
<br>
<br>

Collision Handling
<br>
CollisionHandler.java: An interface or abstract class for handling collisions in the hash table.
<br>
LinearCollisionHandler.java: Implements linear probing method for collision resolution.
<br>
QuadraticCollisionHandler.java: Implements quadratic probing method for collision resolution.

<br>
<br>

Utilities and Iterators
<br>
KeyIterator.java: Provides an iterator for traversing through the keys in the hash table.

<br>
<br>

Custom Exceptions
<br>
ElementNotFoundException.java: Exception thrown when an element is not found in the hash table.
<br>
EmptyCollectionException.java: Exception thrown when an operation is attempted on an empty collection.

<br>
<br>

Database Simulation
<br>
CredentialDatabase.java: Simulates a database, possibly using the hash table for storing and retrieving credentials.

<br>
<br>

Testing
<br>
PublicArrayHashTableTest.java: Contains test cases for ArrayHashTable, likely using JUnit framework.
<br>
<br>

Dependencies
<br>
JUnit (junit-4.12.jar): For writing and executing tests.
<br>
Hamcrest (hamcrest-core-1.3.jar): Provides matchers for testing assertions in JUnit.
