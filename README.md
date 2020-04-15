# COMP 150 Lab 6 - Classes, Construction, and User-Defined Objects

In this lab:

* Classes as object blueprints
* Instances, class data and instance data
* Constructors
* Overloading methods
* Instance methods and class methods
* Access modifiers
* Accessor (getters) and Mutators (setters)
* Intro Encapsulation
* `toString`, Objects, Intro Inheritance

## Classes as object blueprints

At its core, any object is really just data and methods detailing how to interact with the data and with the object's surroundings. A class can be an object blueprint, detailing what data is stored in objects defined by the class (called **instances** of the class) and how those objects edit and reference their data.

Classes use methods called **constructors** to construct objects of their type. For instance, the `Point` in `java.awt` uses a constructor to create `Point` objects, each storing their own coordinates.

### Class data, instance data and `static`

When declaring data in the class scope (i.e. in the body of the class, but not in any nested blocks), you have 2 options:

* The data can be **instance data**. Each unique object constructed by the class contains its own copy of the data (and these copies can vary in value). Instance data (including instance methods) is accessed through instances, with the accessor `.` operator.
* The data can be **class data**. All objects share a single copy of the data. Class data is declared using the `static` keyword. Class data (including class methods) is accessed through the class itself, with the accessor `.` operator.

The `Person` class below contains a single `static long` called `numberOfPeople` (whose purpose is to track the number of instantiated `Person` objects). This single counter is shared by the class and all of its instances. Each instantiated `Person` has its own `name` and `age`.

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    static long numberOfPeople = 0;
    
    // Declare instance data
    String name;
    int age;
}
```

### Constructors

In order to create individual `Person`s, it is necessary to add a **constructor**. A constructor is a special method which is defined using just the class name to denote both the type and the identifier for the constructor. The `Person` constructor below specifies how to create a new `Person`. It takes no arguments, and simply sets the name and age to some default values. The included `main` method constructs two `Person`s and accesses their data.

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    static long numberOfPeople = 0;

    // Declare instance data
    String name;
    int age;

    Person()
    {
        numberOfPeople += 1;
        name = "Joanne Buck";
        age = 0;
    }

    public static void main(String[] args)
    {
        Person joanne = new Person();
        Person john = new Person();

        john.name = "John Doe";
        joanne.age = 47;

        System.out.println(joanne.name + " is " + joanne.age + " years old.");
        System.out.println(john.name + " is " + john.age + " years old.");
        System.out.println("There are " + Person.numberOfPeople + " people.");
    }
}
```

As demonstrated in the `main` above, constructors are called using the `new` keyword.

**<a name="q1"></a>[EXERCISE 1](#a1)** Step through the main above with the debugger and check out the `joanne` and `john` variables in the variables pane. Put breakpoints in the main method and in the `Person()` constructor. When is the constructor called in the main? Where is `joanne.name` set? Why is `john.age` 0?

**<a name="q2"></a>[EXERCISE 2](#a2)** Notice that the `numberOfPeople` long is accessed directly through `Person` in the final print statement, while the `name` and `age` of `joanne` and `john` are accessed through the `Person` instances themselves. Why is this? Are the `name` and `age` instance data or class data? What about the `numberOfPeople`?

**<a name="q3"></a>[EXERCISE 3](#a3)** Look at the declarations for `name`, `age`, and `numberOfPeople` in the `Person` class. In terms of these declarations, how can you tell which pieces of data are instance data and which are class data?

### Overloading

Methods (including constructors) can be **overloaded**, meaning that multiple methods with the same identifier (or multiple "versions" of a method) can be created.

The `Person` class above has a single constructor, `Person()`, which takes no arguments. Below, the class is extended to include an additional constructor in which the name and age are provided as arguments, and the main has been adjusted to use this new constructor.

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    static long numberOfPeople = 0;

    // Declare instance data
    String name;
    int age;


    Person()
    {
        numberOfPeople += 1;
        name = "Joanne Buck";
        age = 0;
    }

    Person(String inputName, int inputAge)
    {
        name = inputName;
        age = inputAge;
    }

    public static void main(String[] args)
    {
        // Create 2 people
        Person joanne = new Person("Joanne Buck", 47);
        Person john = new Person("John Doe", 0);

        System.out.println(joanne.name + " is " + joanne.age + " years old.");
        System.out.println(john.name + " is " + john.age + " years old.");
        System.out.println("There are " + Person.numberOfPeople + " people.");
    }
}
```

**<a name="q4"></a>[EXERCISE 4](#a4)** Run the main method in the class above, and read the outputs. There is a bug: the number of people is not being correctly tracked. Why? Fix the error.

**<a name="q5"></a>[EXERCISE 5](#a5)** The sequence of the types of arguments in a method declaration define its signature. Overloaded methods cannot share the same signature. Try to add another default constructor (i.e. one which which takes no arguments) to the Person class. What happens?

**<a name="q6"></a>[EXERCISE 6](#a6)** Add two more constructors to the `Person` class. The first should take as an argument a `String` representing the `Person`'s, and store that name in the `name` field, and should set the `age` to the default `0`. The second new constructor should take an `int` representing the `Person`'s age; the `age` should be set to this input (if it is valid) and the name should be set to the default `"Joanne Buck"`.

The overloaded `Person` constructor is an example of **polymorphism**. Polymorphism literally means "many forms" (though in the context of overloaded methods it may be more convenient to think of it as "many versions"). We'll talk more about polymorphism in the next lab.

Keep in mind that the method we've chosen to overload is the constructor here, but in generall **any method** can be overloaded.

## Access Modifiers

Data on in the class scope can be declared with the access modifiers `public`, `private`, or `protected`. These modifiers determine what scopes the data can be accessed and modified from. Data with each modifier can be accessed in the scopes denoted in this table:

|                                     | private | default | protected | public |
| :---------------------------------: | :-----: | :-----: | :-------: | :----: |
| **Same class**                      | Yes     | Yes     | Yes       | Yes    |
| **Same package**                    | No      | Yes     | Yes       | Yes    |
| **Different package, subclass**     | No      | No      | Yes       | Yes    |
| **Different package, non-subclass** | No      | No      | No        | Yes    |

**default** is for data with no access modifier.

**<a name="q7"></a>[EXERCISE 7](#a7)** Edit the code in the `Person` class as follows:

* Make the `numberOfPeople` class field and the `name` and `age` instance fields `private` data by adding the `private` heading in front of them. (For instance, replace the declaration  `String name;` with `private String name;`
* Move the `main` method into another class called `PersonClient`.

Then, try to compile and run. What happens (it should be an error)? Theorize about what's going wrong. Don't worry about fixing this error now, we will return to it very soon.

**<a name="q8"></a>[EXERCISE 8](#a8)** Speculate about why one might choose to test a class from a main method located in another class.

## Accessors and mutators

It is generally a good idea to create `private` or `protected` instance variables, and to provide access to them through methods as desired. The goal here is to separate the way the class is **used** from the way it **stores and accesses its data**. If these two things are kept separate, the class itself can be edited without necessitating the editing everywhere the class is used.

The reason(s) this separation (called **encapsulation**) is important will become much more clear as we progress through inheritance and abstraction in upcoming labs, but **you just kinda have to accept it for now**.

The simplest example of this separation of the class's internal workings and its external use is basic **accessor** and **mutator** methods, also known as "getters" and "setters". Accessors allow clients to "get" (i.e. view, see the value of) data, and mutators allow clients to "set" the data (change its value).

We edit the `Person` class below by adding methods to allow `Person` instances' data to be viewed and changed. We also provide a `static` method `getNumberOfPeople()` to let clients access the total number of people, but there is no method allowing clients to set the number of people, as changes to this field should be reserved for the creation or deletion of `Person` objects, which should be done in the `Person` class.

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    private static long numberOfPeople = 0;

    // Declare instance data
    private String name;
    private int age;


    Person()
    {
        numberOfPeople += 1;
        name = "Joanne Buck";
        age = 0;
    }

    Person(String inputName, int inputAge)
    {
        numberOfPeople += 1;
        setName(inputName);
        setAge(inputAge);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int newAge)
    {
        if (newAge >= 0)
        {
            age = newAge;
        }
        else
        {
            System.out.println("Invalid (negative) age provided. Age set to 0 instead.");
            age = 0;
        }
    }

    public static long getNumberOfPeople()
    {
        return numberOfPeople;
    }
}
```

Note that the constructor now uses the `setName` and `setAge` methods instead of setting the age manually. This allows use to check validity of the input name and age in the `setName` and `setAge` methods and not worry about doing so in the constructor.

Editing the client as follows fixes the error. The client now accesses the data through public means provided in the new methods in `Person`.

<a name="PersonClient"></a>

```java
public class Client
{
    public static void main(String[] args)
    {
        // Create 2 people
        Person joanne = new Person("Joanne Buck", 47);
        Person john = new Person("John Doe", 0);

        System.out.println(joanne.getName() + " is " + joanne.getAge() + " years old.");
        System.out.println(john.getName() + " is " + john.getAge() + " years old.");
        System.out.println("There are " + Person.getNumberOfPeople() + " people.");
    }
}
```

**<a name="q9"></a>[EXERCISE 9](#a9)** Add and test private data and public accessor / mutator methods to the `Person` class for the following attributes:

* height
* favorite word

Don't forget to edit the constructors to initialize the data!

## `this`

As we've discussed, `static` elements of a class are **class data** (they are referenced / called through the class itself) while non-`static` elements are **instance data**, which are referenced / called through instances.

Non-`static` methods, i.e. **instance methods**, are done "by an instance" of the class, with access to that instance. The instance making the method call is referred to as `this` within these methods. Below we have a modified version of the `Person` class, which uses the keyword `this` in its instance methods. For example, in the [client above](#PersonClient), when `getName` is accessed through the `Person` instance called `joanne` with `joanne.getName()`, any occurences of `this` in the `getName` method refer to `joanne`. In the `john.getName()` call, `this` refers to `john`.

Instance data like `age` can be referenced as `this.age` in non-static methods, referring to the `age` data for the instance from which the method was accessed. `this` is usually optional; if you type `age` anywhere in a `Person` method, it is assumed to be the `age` from the `Person` class unless another (more local) definition of `age` appears. If, however, there is another variable or argument in an instance method which uses the same identifier as the instance data, then the instance data is "hidden" behind the new variable with the same identifier, so `this` must be used to access the instance data. In the `setAge` method below, `age` alone refers to the argument (the new value to which the age is being set), and not to the instance data, so the instance data with identifier `age` can only be referened through `this.age`, hence the line `this.age = age;`.

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    private static long numberOfPeople = 0;

    // Declare instance data
    private String name;
    private int age;


    Person()
    {
        numberOfPeople += 1;
        setName("Joanne Buck");
        setAge(0);
    }

    Person(String name, int age)
    {
        numberOfPeople += 1;
        setName(name);
        setAge(age);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        if (age >= 0)
        {
            this.age = age;
        }
        else
        {
            System.out.println("Invalid (negative) age provided. Age set to 0 instead.");
            this.age = 0;
        }
    }

    public static long getNumberOfPeople()
    {
        return numberOfPeople;
    }
}
```

## `toString`, `Object`, inheritance

Every class in Java is an extension of the `Object` class. This means that any object is an instance of `Object` class, with some extra stuff. In other words, all classes **inherit** from the `Object` class. We'll talk more about inheritance in a future lab, but for now know that every object is a member of the `Object` class.

Every object needs a `toString` method; if `toString` is not defined in a class, and objects from that class are constructed, then these objects use the `toString` provided in the `Object` class, which returns a `String` containing the name of the class and the address of the object.

**EXERCISE** Edit the [client above](#PersonClient) to print `joanne` instead of `joanne.getName()`. What happens?

Since every class inherits from the `Object` class, every instance of every class **is an `Object`**. If you want to store generic data (data of "any type") in an array or `ArrayList`, this can be done with an array or `ArrayList` of `Object`s.

**EXERCISE** Primitive data isn't stored in objects, so it cannot be stored in an array or `ArrayList` of `Object`s. If you want to store primitive data in an `Object` array, how might you go about it? In other words, what classes would you use to replace primitive data types for storage in an `Object` array?

## Answers to selected exercises

### **<a name="a1"></a>[SOLUTION 1](#q1)**

The constructor is called twice in the main, on the lines `Person joanne = new Person();` and `Person john = new Person();`. During both constructor calls, the instance being constructed has its name set to `"Joanne Buck"` and its age set to `0` in the constructor. After construction, the name field for `john` and the age field for `joanne` are both changed from their default values.

### **<a name="a2"></a>[SOLUTION 2](#q2)**

`name` and `age` are instance data, and `numberOfPeople` is class data. As such, `name` and `age` are specific to instances `john` and `joanne`, while `numberOfPeople` is shared among all `Person`s and is accessed directly through the `Person` class.

### **<a name="a3"></a>[SOLUTION 3](#q3)**

`numberOfPeople` is a `static` variable, so it is class-specific, whereas `name` and `age` are not `static`, so they are instance-specific.

### **<a name="a4"></a>[SOLUTION 4](#q4)**

The new constructor wasn't updating the `numberOfPeople` variable; this has been fixed below:

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    static long numberOfPeople = 0;

    // Declare instance data
    String name;
    int age;


    Person()
    {
        numberOfPeople += 1;
        name = "Joanne Buck";
        age = 0;
    }

    Person(String inputName, int inputAge)
    {
        numberOfPeople += 1;
        name = inputName;
        age = inputAge;
    }

    public static void main(String[] args)
    {
        // Create 2 people
        Person joanne = new Person("Joanne Buck", 47);
        Person john = new Person("John Doe", 0);

        System.out.println(joanne.name + " is " + joanne.age + " years old.");
        System.out.println(john.name + " is " + john.age + " years old.");
        System.out.println("There are " + Person.numberOfPeople + " people.");
    }
}
```

### **<a name="a5"></a>[SOLUTION 5](#q5)**

This creates an error: `'Person()'  is already defined in 'Person'`. While there can be multiple "versions" of a single method, they must take different arguments (i.e. they must have different signatures).

### **<a name="a6"></a>[SOLUTION 6](#q6)**

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    static long numberOfPeople = 0;

    // Declare instance data
    String name;
    int age;


    Person()
    {
        numberOfPeople += 1;
        name = "Joanne Buck";
        age = 0;
    }

    Person(String inputName, int inputAge)
    {
        numberOfPeople += 1;
        name = inputName;
        age = inputAge;
    }

    Person(String inputName)
    {
        numberOfPeople++;
        name = inputName;
        age = 0;
    }

    Person(int inputAge)
    {
        numberOfPeople++;
        name = "Joanne Buck";
        age = 0;
    }

    public static void main(String[] args)
    {
        // Create 2 people
        Person joanne = new Person("Joanne Buck", 47);
        Person john = new Person("John Doe", 0);

        System.out.println(joanne.name + " is " + joanne.age + " years old.");
        System.out.println(john.name + " is " + john.age + " years old.");
        System.out.println("There are " + Person.numberOfPeople + " people.");
    }
}
```

### **<a name="a7"></a>[SOLUTION 7](#q7)**

With the `Person` class defined like this (maybe with a couple more constructors from exercise 6):

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    private static long numberOfPeople = 0;

    // Declare instance data
    private String name;
    private int age;


    Person()
    {
        numberOfPeople += 1;
        name = "";
        age = 0;
    }

    Person(String inputName, int inputAge)
    {
        numberOfPeople += 1;
        name = inputName;
        age = inputAge;
    }
}
```

run the `main` from another class, something like

```java
public class Client
{
    public static void main(String[] args)
    {
        // Create 2 people
        Person joanne = new Person("Joanne Buck", 47);
        Person john = new Person("John Doe", 0);
        
        // Print their data
        System.out.println(joanne.name + " is " + joanne.age + " years old.");
        System.out.println(john.name + " is " + john.age + " years old.");
        System.out.println("There are " + Person.numberOfPeople + " people.");
    }
}
```

The project should not compile. The error states that the data has private access (meaning it cannot be accessed outside of the `Person` class).

###**<a name="a8"></a>[SOLUTION 8](#q8)**

A main located in the class being tested will have `private` access, meaning it might not function the same as it would in another class, because it might have access to things it wouldn't in a `public` scope. If the class being tested is intended for use in other classes, this will necessitate some testing from other classes.

### **<a name="a9"></a>[SOLUTION 9](#q9)**

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    private static long numberOfPeople = 0;

    // Declare instance data
    private String name;
    private int age;
    private double height;
    private String favoriteWord;


    Person()
    {
        numberOfPeople += 1;
        setName("Joanne Buck");
        setAge(0);
        setHeight(0.0);
        setFavoriteWord("word");
    }

    Person(String inputName, int inputAge)
    {
        numberOfPeople += 1;
        setName(inputName);
        setAge(inputAge);
        setHeight(0.0);
        setFavoriteWord("word");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int newAge)
    {
        if (newAge >= 0)
        {
            age = newAge;
        }
        else
        {
            System.out.println("Invalid (negative) age provided. Age set to 0 instead.");
            age = 0;
        }
    }

    public double getHeight()
    {
        return height;
    }
    
    public void setHeight(double newHeight)
    {
        height = newHeight;
    }
    
    public String getFavoriteWord()
    {
        return favoriteWord;
    }
    
    public void setFavoriteWord(String newFavoriteWord)
    {
        favoriteWord = newFavoriteWord;
    }

    public static long getNumberOfPeople()
    {
        return numberOfPeople;
    }
}
```

# Lab Tasks

## Task 1

Modify the `Person` class so that it stores a `firstName` and a `lastName` instead of just storing a `name`. All other instance data (`age`, `favoriteWord`, `height`, ...) should be left out of this class.

Your class should contain the following constructors:

* `Person()`
	* set the first and last name to `"Joanne"` and `"Buck"` respectively.
* `Person(String fullName)`
* `Person(String firstName, String lastName)`

Your class should also contain the following methods. The constructors should use the mutator (i.e. "setter") methods instead of directly setting private values:

* `public void setName(String fullName)`
	* You'll need to split the `String` into pieces using space as the delimiter.
	* If only 1 name is supplied, the first name should be assigned to the empty `String`.
* `public void setName(String firstName, String lastName)`
* `public String getFirstName()`
* `public void getLastName()`
* `public String getInitials()`
	* Finds the person's first and last initials, stored together in a single `String`
	* If no first name is stored, there should be no first initial.
* `public boolean hasSameInitials(Person otherPerson)`
	* Checks if the person `this` has the same initials as the other person `otherPerson`.

Create a main to test your new `Person` class.

## Task 2

Download or copy the provided [Fraction.java](./Fraction.java) and [FractionCalculator.java](./FractionCalculator.java).

`Fraction` instances store 2 pieces of instance data: the numerator and denominator, in `int` form. It contains two constructors, and several completed methods near the top. There are several incomplete methods with `//TODO` in them; these methods need to be completed. There are comments describing what each method needs to accomplish.

Create a client to test your `Fraction` class in the comments in the `FractionCalculator`'s main method.

