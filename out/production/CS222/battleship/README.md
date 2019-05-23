# Battleships

In the game of _Battleships_ (Amiral Battı),
there is a battle field, which is a grid of size _width_ and _height_, 
and a number of ships on the battle field. 
Currently we have boat, submarine, and destroyer as possible ship kinds,
whose shapes are shown in the `battleship.png` image. 
However, new ship kinds may be requested
later, potentially with odd (garip) shapes as also shown in the image.

Implement the `BattleField`, `Ship`, `Boat`, `Destroyer`, `Submarine` classes, and
any other classes that you think are necessary.
You should not implement GUI-related things.
This exam is about implementing the model.

Requirements:

* A `BattleField` is created by specifying the dimensions of the grid. E.g.:
    
    ```java 
    BattleField field = new BattleField(20, 30);
    ```

---------------    The constructor raises an `IllegalArgumentException` if the given
    width or height value is not positive.

* A `Ship` may be put to a specific location on a `BattleField`.
  Here, the "location" refers to the top-left cell of the imaginary rectangle
  that surrounds the ship.
  
    ```java
    Ship ship = ...;
    field.put(6, 7, ship);    
    ```

  For instance, take a look at the `battlefield.png` file.
  In that 10x10 battle field, there is a boat
  at location (4, 8), a submarine at location (5, 1),
  a destroyer at location (1, 4), and another submarine at (6, 5).

--------------  If the ship is put at a location that makes some or all parts of the
  ship to be out of the bounds of the field,
  `IllegalArgumentException` should be thrown.

*  We can shoot at the battle field for a specific location. 
   After shooting for at least 3 times, the `getReport()` method of 
   the `BattleField` returns the `Set` of ships that got hit by the 
   most recent 3 shoots. 
   If the `getReport()` method is invoked before making at least 3 shots
   the method simply returns an empty set even if some ships were hit.
   For example, for the battle field shown above:
   
    ```java
    field.shoot(5, 2); // hits the upper submarine
    field.shoot(5, 4); // a miss 
    field.shoot(2, 5); // hits the destroyer
    Set<Ship> set1 = field.getReport(); 
    // set1 contains the upper submarine and the destroyer

    field.shoot(5, 8); // hits the boat
    field.shoot(7, 1); // a miss
    field.shoot(4, 4); // a miss 
    field.shoot(1, 4); // a miss
    Set<Ship> set2 = field.getReport(); 
    // set2 is empty; the most recent 3 shots are misses. 

    field.shoot(5, 2); // hits the upper submarine
    field.shoot(2, 5); // hits the destroyer
    Set<Ship> set33 = field.getReport(); 
    // set3 is empty; less than 3 shots since the last report.
    ``` 

   If the shot is made to a location that is out of the bounds of the field,
   it simply counts as a miss -- no exception should be thrown.

## Task

* Write test cases for the `BattleField` class. ONLY this class.
* Implement the `BattleField`, `Ship`, `Boat`, `Destroyer`, and `Submarine` classes.

**You may add new public/private fields/methods to the 
given classes.
You may also create new classes.
You may NOT change the signature of the given public methods.** 

Use the `HashSet` class for the `Set` implementation.
`Set<E>` has the following useful methods:
* `int size()`
* `boolean isEmpty()`
* `boolean contains(E e)`
* `void add(E e)`

## Grading

* Correctness: 40 pts 
* Cleanness: 40 pts 
* Quality and coverage of test cases: 20 pts 
 
