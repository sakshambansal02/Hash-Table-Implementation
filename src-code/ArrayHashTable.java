package structures;

import java.util.Iterator;

//This class implements a hashtable for a generic key and value type using an array backend.
public class ArrayHashTable<K, V> implements HashTable<K, V> {

  //Default constructor values- DO NOT CHANGE.
  public static final int DEFAULT_CAPACITY = 5;
  public static final double DEFAULT_LOAD_FACTOR = 0.7;

  //Provided instance variables- DO NOT CHANGE
  protected K[] keyArray;  //stores all keys 
  protected V[] valueArray;  // stores all values
  protected boolean[] isActiveArray;  // if true the cell is active, false the cell has data to be deleted.
  protected CollisionHandler <K> collisionHandler;  // collision handler can be either linear or quadratic.
  protected int count; // counts the number of key/value pairs on the hashtable
  protected int capacity;  // length of the arrays
  private double loadFactor;  // the ratio (0 < loadFactor < 1) that determines when to resize the hashtable.

  /**
   *   Initialize count to 0, capacity and loadFactor to the respective DEFAULT values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(CollisionHandler <K> collisionHdler) {
      //TODO: Implement this method.
      this.count=0; 
      this.collisionHandler =collisionHdler; 
      capacity =DEFAULT_CAPACITY; 
      loadFactor = DEFAULT_LOAD_FACTOR; 
      keyArray =(K[])new  Object[capacity] ;
      valueArray = (V[])new Object[capacity]; 
      isActiveArray = new boolean[capacity];

  }


  /**
   *   Initialize count to 0, capacity to the argument, loadFactor to the respective DEFAULT values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(int capacity, CollisionHandler <K> collisionHdler){
      //TODO: Implement this method.
      this.count=0; 
      this.collisionHandler =collisionHdler; 
      this.capacity =capacity; 
      loadFactor = DEFAULT_LOAD_FACTOR; 
      keyArray =(K[])new  Object[capacity] ;
      valueArray = (V[])new Object[capacity]; 
      isActiveArray = new boolean[capacity];

  }


  /**
   *   Initialize count to 0, capacity and loadFactor to the respective argument values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(int capacity, double loadFactor, CollisionHandler <K> collisionHdler){
      //TODO: Implement this method.
      this.count=0; 
      this.collisionHandler =collisionHdler; 
      this.capacity =capacity; 
      this.loadFactor =loadFactor; 
      keyArray =(K[])new  Object[capacity] ;
      valueArray = (V[])new Object[capacity]; 
      isActiveArray = new boolean[capacity];

  }


  /** 
   * Returns the number of elements in the hash table.
   */
  public int getSize() {
      //TODO: Implement this method.
      return count;
  }

   /**
   * One approach: Double the capacity of the hashtable. Create new, larger arrays.
   * For each key in the key array, if that cell is active (true) in
   * the activeArray, get the index by calling the hash function, use the collisionHandler
   * probe method to get the new index (it may be different from the original hash if
   * a collision occurred). Enter the key in the new keyArray, value in the new valueArray,
   * and set the new activeArray to true. Finally, assign the current keyArray, valueArray,
   * and activeArray to the new arrays.
   */
  private void resizeArray() {
   //TODO: Implement this method
   int Capacity2 = capacity;
   capacity = capacity * 2;

   K[] tKey= (K[]) new Object[capacity];
   V[] tVal = (V[]) new Object[capacity];
   boolean[] tActive = new boolean[capacity];

   for(int i=0; i<Capacity2; i++){
     if(isActiveArray[i]){ 
       int index = Math.abs(keyArray[i].hashCode() % capacity);
       if(tActive[index]){
         index = collisionHandler.probe(index, tActive, capacity);
       }
        tKey[index] = keyArray[i];
        tVal[index] = valueArray[i];
        tActive[index] = true;
       
     }

   
   }
    keyArray = tKey;
    valueArray = tVal;
    isActiveArray = tActive; 
      
  }


  /**
  * Calculates and returns the load factor of the hash table.
  * This is the ratio of the number of elements stored in the hashtable
  * divided by the total capacity of the hashtable.
  */
  private double calcLoadFactor() {
   //TODO: Implement this method
   double fraction = (double)count / capacity;
   return fraction;
  } 

  /** 
   * Insert the provided key value pair into the hashtable. If the key exists in 
   * the table, the stored value is overwritten with the new value passed in.
   * Please use the provided getHash method to get the hash for the array index.
   * Steps: check is current load is > loadFactor. If true, call resizeArray.
   * Then see if key is in the table by calling getIndex. If key exists, overwrite value
   * in the valueArray. If key does not exist, get the index by calling the hash function (getHash).
   * Then call resolveCollision to see if a collision occurs and if so, that methos will use the 
   * collision handler to resolve it. Then enter the key in the keyArray using the index returned from 
   * resolveCollision, enter the value in the valueArray, and set the isActiveArray to true. Finally,
   * increment the count.
   */
  public void put(K key, V value) {
      //TODO: Implement this method.
      if(calcLoadFactor() >= loadFactor){
        resizeArray();
      }  
      int pointer = key.hashCode()%capacity; 

      if(pointer < 0){
        pointer *= -1;
      }  
      
      int test = collisionHandler.search(pointer, key, keyArray, isActiveArray, capacity); 
      if(test == pointer){
        valueArray[pointer]=value;
        return;
      }
      if(isActiveArray[pointer]==true && keyArray[pointer]!=null ){
        CollisionHandler <K> collisionPointer = getCollisionHandler(); 
        pointer = collisionPointer.probe(pointer, isActiveArray,capacity );
      }  
      
      isActiveArray[pointer]= true; 
      keyArray[pointer] =key; 
      valueArray[pointer]=value; 
      count++;

  }

  /**
   * Returns the value associated with the key if it exists in the hashtable.
   * Get the index by calling the hash function, then check if key exists by calling getIndex.
   * Return the value in the valueArray if key is in the table.
   * Returns null if the key is not in the table.
   */
  public V getValue(K target) {
   //TODO: Implement this method
   int pointer = Math.abs((target.hashCode())%capacity); 
   CollisionHandler<K> search = getCollisionHandler(); 
   int searchIndex = search.search( pointer,  target,  keyArray,  isActiveArray, capacity);
   if(pointer!=searchIndex){
     pointer = searchIndex;
   }
   if(pointer == -1){
     return null;
   }
   if(keyArray[pointer].equals(target) && isActiveArray[pointer]== true){
     return valueArray[pointer];
   }
      return null;
      
  }


  /**
   * Removes the key-value pair specified by the targetKey from the hashtable and returns the value. 
   * The method gets the hash on the targetKey, then checks if the key is in the table by calling getIndex.
   * Then, if targetKey is in the table, get the value associated with the targetKey from the valueArray,
   * then set the activeArray to false as this cell is now open, decrement the count, and return the value.
   * 
   * @throws ElementNotFoundException if the target does not exist in the table.
   */
  public V remove(K targetKey)throws ElementNotFoundException {
   //TODO: Implement this method
   int pointer = Math.abs(targetKey.hashCode()%capacity); 
   int searchPointer = collisionHandler.search(pointer,  targetKey,  keyArray,  isActiveArray, capacity); 
   if(searchPointer==-1){
    throw new ElementNotFoundException("Element not there ");
  } 
   V newVal = valueArray[searchPointer]; 
     isActiveArray[searchPointer]=false;
    count --; 
     return newVal;
  
}

  

  /**
   *  Returns a KeyIterator for the keys in this HashTable. 
   */
  public Iterator<K> keyIterator(){
       return new KeyIterator<K>(keyArray, count);
  }

      /**
   * This method ensures that the input number maps to a valid array index.
   */  
  private int getBoundedHash(int input) {
    return Math.abs(input) % this.capacity;
  }

    /**
   * This method uses the hashCode() method to get the standard hash of the given key.
   */
  private int getHashOfKey(K key) {
    int index = getBoundedHash(key.hashCode());
    return index;
  }
  
   /**
   * This method returns an open array index based on 
   * which collision handler is in use.
   * 
   * @param index The array index specified by the hash of key K
   */
  private int resolveCollision(int index) {
      index = collisionHandler.probe(index, isActiveArray, capacity);
    return index;
  }

  /**
   * This method returns the array index for the value associated with the provided key.
   * Returns -1 if the key is not in the hash table.
   * @param index An array index to start searching from.
   * @param K The key used in the search
   */
  private int getIndex(int index, K key) {
      index = collisionHandler.search(index, key, keyArray, isActiveArray, capacity);
    return index;
  }

    /******** methods used for testing purposes ***********/
    public int getCapacity(){
      return capacity;
    }

    public double getLoadFactor(){
      return loadFactor;
    }
  
    public K[] getKeyArray(){
       return keyArray;
    }  
  
    public V[] getValueArray(){
      return valueArray;
   }

   public boolean[] getIsActiveArray(){
     return isActiveArray;
   }

   public CollisionHandler <K>  getCollisionHandler(){
     return collisionHandler;
   }
  /************************************************************/

   /**  
    * Use this code for testing your ArrayHashTable. 
    * Comment out statements as needed. Use the debugger to see
    * what your code is doing by stepping in, out, and over methods
    * and statements, and checking the variables pane to see the state 
    * of your table and its variables.
    */
  public static void main(String[] args) throws Exception {
      // Init the type of collision handler: linear or quadratic.
    // CollisionHandler <String> collisionHdler = new LinearCollisionHandler<>();
      CollisionHandler <String> collisionHdler = new QuadraticCollisionHandler<>();
     ArrayHashTable<String, String> table = new ArrayHashTable<String, String> (collisionHdler);
     table.put("fatimeh", "ef4#B%k");
     // test the get method after the put method was called:
     System.out.println("test get after put: "+table.getValue("fatimeh"));
     table.put("matt", "A9d%&b");
     table.put("fadhil", "2h*k9s");
     table.put("rumeng", "j8*shX2");
    // this next call should cause a resize- uncomment when the table works on the above data:
     table.put("harper", "m8Ut6%#a");
    // test the keyIterator
     Iterator<String> keyIter = table.keyIterator();
     while(keyIter.hasNext()){
       String curKey = keyIter.next();
       System.out.print(curKey+ " ");
       System.out.println(table.getValue(curKey)+", ");
     }
    // test removing 
    table.remove("matt");
    System.out.println("test remove: "+table.getValue("matt"));
  }
}
