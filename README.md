# Object-Oriented Programming - Classes, Construction, and User-Defined Objects

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

Classes use methods called **constructors** to construct objects of their type. For instance, the `Point` class in `java.awt` uses a constructor to create `Point` objects, each storing their own integer coordinates.

### Class data, instance data and `static`

When declaring data in the class scope (i.e. in the body of the class, but not in any nested blocks), you have 2 options:

* The data can be **instance data**. Each unique object constructed by the class contains its own copy of the data (and these copies can vary in value). Instance data (including instance methods) is accessed through instances, with the accessor `.` operator.
* The data can be **class data**. All objects share a single copy of the data. Class data is declared using the `static` keyword. Class data (including class methods) is accessed through the class itself, with the accessor `.` operator.

The `Person` class below contains a single `static long` called `numberOfPeople` (whose purpose is to track the number of instantiated `Person` objects). This single counter is shared by the class and all of its instances. It also has two nonstatic fields, `name` and `age`. Because these fields are nonstatic, each instantiated `Person` has its own `name` and `age`.

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

In order to create individual `Person`s, it is necessary to add a **constructor**. A constructor is a special method which is defined using just the class name to denote both the type and the identifier. The `Person` constructor below specifies how to create a new `Person`. It takes no arguments, and simply sets the name and age to some default values. The included `main` method constructs two `Person`s and accesses their data.

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

The `Person` class above has a single constructor, `Person()`, which takes no arguments; this is commonly called the **default constructor**. Below, the class is extended to include an additional constructor in which the name and age are provided as arguments, and the main has been adjusted to use this new constructor.

```java
public class Person
{
    // Declare and initialize class data (shared by all Persons)
    static long numberOfPeople = 0;

    // Declare instance data
    String name;



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
        
        // Print their data
        System.out.println(joanne.name + " is " + joanne.age + " years old.");
        System.out.println(john.name + " is " + john.age + " years old.");
        System.out.println("There are " + Person.numberOfPeople + " people.");
    }
}
```

**<a name="q4"></a>[EXERCISE 4](#a4)** Run the main method in the class above, and read the outputs. There is a bug: the number of people is not being correctly tracked. Why? Fix the error.

**<a name="q5"></a>[EXERCISE 5](#a5)** The sequence of the types of arguments in a method declaration define its signature. Overloaded methods cannot share the same signature. Try to add another default constructor (i.e. one which which takes no arguments) to the Person class. What happens?

**<a name="q6"></a>[EXERCISE 6](#a6)** Add two more constructors to the `Person` class. The first should take as an argument a `String` representing the `Person`'s name, and store that name in the `name` field, and should set the `age` to the default `0`. The second new constructor should take an `int` representing the `Person`'s age; the `age` should be set to this input (if it is valid) and the name should be set to the default `"Joanne Buck"`.

The overloaded `Person` constructor is an example of **polymorphism**. Polymorphism literally means "many forms" (though in the context of overloaded methods it may be more convenient to think of it as "many versions"). We'll talk more about polymorphism in the next lab.

Keep in mind that the method we've chosen to overload is the constructor here, but in general **any method** can be overloaded.

## Access Modifiers

Data in the class scope can be declared with the access modifiers `public`, `private`, or `protected`. These modifiers determine what scopes the data can be accessed and modified from. Data with each modifier can be accessed in the scopes denoted in this table:

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

**<a name="q8"></a>[EXERCISE 8](#a8)** Speculate about why one might choose to test a class from a main method located in another class, as opposed to simply including a main in the class definition to test it there.

## Accessors and mutators

It is generally a good idea to create `private` or `protected` instance variables, and to provide access to them through methods as desired. The goal here is to separate the way the class is **used** from the way it **stores and accesses its data**. If these two things are kept separate, the class itself can be edited without necessitating editing everywhere the class is used, so code bases which use the class are much easier to maintain.

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

Note that the constructor now uses the `setName` and `setAge` methods instead of setting the age manually. This allows us to check validity of the input name and age in the `setName` and `setAge` methods and not worry about doing so in the constructor; in other words, we only have to validate inputs in one place, and can avoid repeating this work in the constructors.

Now that we've created an interface through which the `name` and `age` fields can be set and viewed even though they are private, we can edit the client as follows fixes the error explored earlier. The client now accesses the data through public means provided by the new methods in `Person`.

<a name="PersonClient"></a>

```java
public class Client
{
    public static void main(String[] args)
    {
        // Create 2 people
        Person joanne = new Person("Joanne Buck", 47);
        Person john = new Person("John Doe", 0);
        
        // Print their data
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

Nonstatic methods, i.e. **instance methods**, are done "by an instance" of the class, with access to that instance's data. The instance making the method call is referred to as `this` within these methods. Below we have a modified version of the `Person` class, which uses the keyword `this` in its instance methods.

In the [client above](#PersonClient), when `getName` is accessed through the `Person` instance called `joanne` with `joanne.getName()`, any occurences of `this` in the `getName` method refer to `joanne`. In the `john.getName()` call, `this` refers to `john`.

Instance data like `age` can be referenced as `this.age` in nonstatic methods, referring to the `age` data for the instance from which the method was accessed. `this` is usually optional; if you type `age` anywhere in a nonstatic `Person` method, it is assumed to be the `age` from the `Person` instance unless another (more local) definition of `age` appears. If, however, there is another variable or argument in an instance method which uses the same identifier as the instance data, then the instance data is "hidden" behind the new variable with the same identifier, so `this` must be used to access the instance data. In the `setAge` method below, `age` alone refers to the argument (the new value to which the age is being set), and not to the instance data, so the instance data with identifier `age` can only be referened through `this.age`, hence the line `this.age = age;`.

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

One effect of this `Object` inheritance is that all classes have a constructor, even if one isn't defined, because the `Object` class has a constructor. This is why we can make `new Math()` objects, even though the `Math` class doesn't have any constructors of its own, nor does it have any nonstatic data or methods.

Most object-blueprint classes need a `toString` method; if `toString` is not defined in a class, and objects from that class are constructed, then these objects use the `toString` provided in the `Object` class. `Object.toString` is somewhat uninformative; it returns a `String` containing the name of the class and the address of the instance. When an instance is printed, such as `System.out.println(joanne);`, the `toString()` method is called implicitly. That is, `System.out.println(joanne);` is equivalent to `System.out.println(joanne.toString);`.

**EXERCISE 10** Edit the [client above](#PersonClient) to print `joanne` instead of `joanne.getName()`. What happens?

**EXERCISE 11** Add a `toString` method to the `Person` class. This method should construct and return a `String` containing some identifying information about the `Person` instance from which it is called.

Since every class inherits from the `Object` class, every instance of every class **is an `Object`**. If you want to store generic data (data of "any type") in an array or `ArrayList`, this can be done with an array or `ArrayList` of `Object`s like this: `ArrayList<Object> myGenericList = new ArrayList<Object>();`.

**EXERCISE 12** Primitive data isn't stored in objects, so it cannot be stored in an array or `ArrayList` of `Object`s. If you want to store primitive data in an `Object` array, how might you go about it? In other words, what classes would you use to replace primitive data types for storage in an `Object` array?

## Answers to selected exercises

### **<a name="a1"></a>[EXERCISE 1](#q1)**

The constructor is called twice in the main, on the lines `Person joanne = new Person();` and `Person john = new Person();`. During both constructor calls, the instance being constructed has its name set to `"Joanne Buck"` and its age set to `0` in the constructor. After construction, the name field for `john` and the age field for `joanne` are both changed from their default values.

### **<a name="a2"></a>[EXERCISE 2](#q2)**

`name` and `age` are instance data, and `numberOfPeople` is class data. As such, `name` and `age` are specific to instances `john` and `joanne`, while `numberOfPeople` is shared among all `Person`s and is accessed directly through the `Person` class.

### **<a name="a3"></a>[EXERCISE 3](#q3)**

`numberOfPeople` is a `static` variable, so it is class-specific, whereas `name` and `age` are not `static`, so they are instance-specific.

### **<a name="a4"></a>[EXERCISE 4](#q4)**

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

### **<a name="a5"></a>[EXERCISE 5](#q5)**

This creates an error: `'Person()'  is already defined in 'Person'`. While there can be multiple "versions" of a single method, they must take different arguments (i.e. they must have different signatures).

### **<a name="a6"></a>[EXERCISE 6](#q6)**

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

### **<a name="a7"></a>[EXERCISE 7](#q7)**

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

### **<a name="a8"></a>[EXERCISE 8](#q8)**

A main located in the class being tested will have `private` access, meaning it might not function the same as it would in another class, because it might have access to things it wouldn't in a `public` scope. If the class being tested is intended for use in other classes, this will necessitate some testing from other classes.

### **<a name="a9"></a>[EXERCISE 9](#q9)**

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
        int spaceIndex = newFavoriteWord.indexOf(' ');
        if (spaceIndex == -1)
            favoriteWord = newFavoriteWord;
        else
        {
            System.out.println("Too many words in setFavoriteWord, first word used.");
            favoriteWord = newFavoriteWord.substring(0, spaceIndex);
        }
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
	* If more than 2 names are supplied, only the first and last should be used; those in the middle should be omitted.
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

Download or copy the provided [Fraction.java](./Fractions/Fraction.java) and [FractionCalculator.java](./Fractions/FractionCalculator.java).

`Fraction` instances store 2 pieces of instance data: the numerator and denominator, in `int` form. It contains two constructors, and several completed methods near the top. There are several incomplete methods with `//TODO` in them; these methods need to be completed. There are comments describing what each method needs to accomplish.

Create a client to test your `Fraction` class in the `FractionCalculator`'s main method; commented instructions are provided outlining how the client should proceed.

## Task 3

In this task, you will create a class called `Book`. A client has been provided to test your class in [BookClient.java](./Books/BookClient.java). You should read the messages that it provides, starting at the top (the output will be somewhat long, so you'll need to scroll up). You should debug the methods in the order that the client tests them; tests further down in the client are not valid tests if the methods tested above them are not correct.

### Instance Data

The `Book` class should contain three `private` attributes, all `String`s. These fields are intended to store the book's title, author, and ISBN. These should all be should be `final`; once a book has been created, none of its attributes can change.

### Constructor

There should be no default constructor; the only constructor should take three `String` arguments: the book's title, author, and ISBN, in that order.

The title should be stored in its entirety in the appropriate attribute.

If the provided author's name contains any spaces, the last name (i.e. the substring consisting of all characters after the last space) should be stored in the author field. If the provided name contains no spaces, it should be assumed to be the last name and should be stored in the author field.

The ISBN should consist of exactly 10 digits and nothing else. ISBNs are often written with dashes separating different groups of digits, but that will not be the case for these ISBNs. It should be checked that the ISBN is valid by checking if the last digit (the **checksum**) is correct.

Details regarding how to calculate the checksum from the preceeding digits can be found [here](https://en.wikipedia.org/wiki/International_Standard_Book_Number#ISBN-10_check_digit_calculation); the link should take you to a section labelled "ISBN-10 check digit calculation", which is the section that you'll need. You may also find [this ISBN calculator](http://www.hahnlibrary.net/libraries/isbncalc.html) helpful to test your understanding of how the check digit is calculated. Note that the check digit digit will be represented with the character `'X'` (capital) if it would otherwise be 10; your validation will need to account for this.

Your process for validating an ISBN should be as follows:

* Ensure that it has exactly 10 characters.
* Ensure that the first 9 characters are all digits.
* Ensure that the 10th character (the check digit, or checksum) is correct (a capital `'X'` will be used if the checksum is 10; otherwise the appropriate digit will be used).

If the ISBN is invalid, an `InvalidISBNException` should be thrown, with the invalid ISBN in string form as an argument. This can be done with the statement `throw new InvalidISBNException( invalidISBN );`, where `invalidISBN` is the the variable containing the ISBN string that is determined to be invalid. In order to support this exception, you will need to:

1. Include [InvalidISBNException.java](./Books/InvalidISBNException.java) in the directory with Book.java. 
	* Feel free to read through it to learn how to define minimal exceptions of your own! We'll discuss `super` in a future lab.
2. Allow the constructor to throw this exception. The constructor definition should look like:

```java
public Book(String title, String author, String isbn) throws InvalidISBNException
{
	<constructor body>
}
```

### Accessors

Three public accessor methods, `getTitle`,  `getAuthor`, and `getISBN` should be created to provide an interface through which a book's data can be accessed, but not edited. They should take no arguments and should return the corresponding field.

### Mutators

`Book` should have no mutator methods. There is no need for a `setAuthor` method, for instance, because the `author` is final, so its value is set exactly once in the constructor and cannot be changed by a mutator.

### Other Instance Methods

* `toString` :
	* public
	* no arguments
	* returns a `String` containing the ISBN, a colon, the title in double quotes followed by the word "by", and then the author's last name, e.g.:
		* `981323590X : "An Intoduction to the Analysis of Algorithms" by Soltys`
		* `0439784549 : "Harry Potter and the Half-Blood Prince" by Rowling`
* `equals` :
	* public
	* 1 argument, of type `Book` (the other book to compare `this` to)
	* returns true of the two books have the same ISBN, and false otherwise
* `hasTitle`
	* public
	* 1 argument, a `String`, assumed to be a book title
	* return true if the provided `String` is the `Book`'s title, and false otherwise
* `isWrittenBy`
	* public
	* 1 argument, a `String`, assumed to be an author's last name
	* return true if the provided `String` is the `Book`'s author's last name, and false otherwise

### Other Methods

No other methods are required. You may, of course, add other methods to facilitate the tasks above. For instance, I added `private static boolean validateISBN(String isbn)`, which returns true if the input `String` is a valid ISBN and false otherwise. I also added `private static String extractLastName(String fullName)`, for use in the constructor when saving the author's last name. Any additional methods should be private; no extra access should be provided to classes outside of the `Book` class.

## Task 4

In this task, you will complete the provided [BookShelf.java](./Books/BookShelf.java) by filling out the constructor and the incomplete methods. The `BookShelf` class will create and maintain a 2D array of `Book`s. Once you've completed the `add` method, try running the provided [BookShelfClient.java](./Books/BookShelfClient.java); the results should look something like this:

 ```
 -------------------------------------------------------------------------------------
 |         0385343019        |         0385343019        |         0439784549        |
 |     The Gates of Rome     |     The Gates of Rome     | Harry Potter and the Half |
 |          Iggulden         |          Iggulden         |          Rowling          |
 -------------------------------------------------------------------------------------
 |         0439784549        |         0385343019        |         0439784549        |
 | Harry Potter and the Half |     The Gates of Rome     | Harry Potter and the Half |
 |          Rowling          |          Iggulden         |          Rowling          |
 -------------------------------------------------------------------------------------
 |         981323590X        |         0345391837        |         0345391837        |
 | An Introduction to the An | So Long, and Thanks for A | So Long, and Thanks for A |
 |           Soltys          |           Adams           |           Adams           |
 -------------------------------------------------------------------------------------
 |         0385343019        |         981323590X        |         0385343019        |
 |     The Gates of Rome     | An Introduction to the An |     The Gates of Rome     |
 |          Iggulden         |           Soltys          |          Iggulden         |
 -------------------------------------------------------------------------------------
 |         981323590X        |         0439784549        |         0439784549        |
 | An Introduction to the An | Harry Potter and the Half | Harry Potter and the Half |
 |           Soltys          |          Rowling          |          Rowling          |
 -------------------------------------------------------------------------------------
 ```

As you implement methods in the `BookShelf` class, expand the `BookShelfClient` to test your newly implemented methods. Feel free to add some more input data to the increase the variety of books on the shelves too!

## Task 5

In this task, you will recreate a task you did in an earlier lab, but in an object-oriented style. Your task is to implement and test a `Triangle` class meeting the following description.

### Instance Data

Each triangle should store its instance data as an array of three doubles, denoting its side lengths. This array should be private, and no methods should be provided which allow clients to mutate it. This should make triangle instances **immutable** to clients; once they have been constructed, their data cannot be changed.

### Constructors

The triangle should have two constructors. The first (default) constructor should take no arguments, and should set all three side lengths to 1. The second should take three double arguments, and should validate them and then store them in its array of side lengths. If the side lengths are invalid, an `InvalidTriangle` exception should be thrown. You will need to create this exception; use the `InvalidISBNException` provided in a previous task as an example.

Three side lengths are valid inputs for triangle side lengths if:

* None of them are negative.
* They obey the [triangle inequality](https://en.wikipedia.org/wiki/Triangle_inequality), which essentially states that the longest side is not longer than the sum of the two smaller siders.

While undergoing construction, during validation, you should sort the three sides. This will make it easier to check that the triangle inequality is met, and will also make the `equals` method much easier. It doesn't matter if you sort in increasing or decreasing order, as long as you are consistent.

### Accessors

The triangle class should have the following (public) accessor methods:

* `getSides` : Returns a **copy** of the array used to store the three side lengths. Should not return the original array; arrays are passed by reference, so returning the original would allow clients to edit the side lengths after construction.
* `getAngles` : Returns an array of three doubles, denoting the angles in the triangle in radians. You will need to use the [law of cosines](https://en.wikipedia.org/wiki/Law_of_cosines) to calculate the angles.
* `getPerimeter` : Returns the perimeter of the triangle, as a double.
* `getArea` : Returns the area of the triangle, as a double. You may find [Heron's forumla](https://en.wikipedia.org/wiki/Heron%27s_formula) useful here.

### Mutators

No mutators should be provided. `Triangle` objects are immutable.

### Other Methods

* `toString` : Returns a String representation of the triangle. Something like `"Triangle(1,1,1)"` (where the 1's are the side lengths) would do.
* `equals` : 
	* Takes another `Triangle` as an argument. 
	* Returns true if the two triangles' side lengths are "equal" and false otherwise. Two side lengths should be treated as equal if their difference is less than `0.00001`.
	* This method will be much easier if the side lengths were sorted during construction.

## Task 6

In this task, you will implement the a data structure called a linked list. Linked lists are data structures which, like arrays, exist to store multiple values, usually of a single type. They will be among the first structures you will cover in most data structures courses.

A linked list is composed of 0 or more nodes. Each node stores a single value (its data) and a reference to the next node in the list. For instance, consider an array storing three integers `{1, 2, 3}`. The corresponding linked list would look like this:

 ![](./figures/linkedList.png)
 
As you can see in the image above, the first node in the linked list is referred to as the "head" and the last one is referred to as the "tail". The reference in the tail which would point to the next node is `null`; there is no next node.

You may find it helpful to watch [this video on linked lists](https://www.youtube.com/watch?v=WwfhLC16bis&t=69s) before moving on, but note that there are differences between the implementation made in the video and the implementation described below.

You are to implement a linked list which stores `int` data. Your implementation will be broken into two classes. The first is the `LinkedListNode` class, which will contain data and methods for the individual nodes within the list. The second is the `LinkedList` class itself, which will store references to the head and tail of the list, some metadata (such as the length of the list), and will define several methods for mutating and accessing the list.

### `LinkedListNode`

Complete the `LinkedListNode` class described below. Then, use the provided [LinkedListNodeClient.java](./LinkedLists/LinkedListNodeClient.java) to test it.

* Attributes:
	* `private int data` : the integer stored in the node.
	* `private LinkedListNode next` : the reference to the next node in the list.

`LinkedListNode`'s will also have public methods, which provide an interface through which clients can create, access and mutate individual nodes. They are:

* Constructors:
	* `public LinkedListNode()` : constructs a new node, with its `data` attribute set to 0 and its `next` attribute set to `null`.
	* `public LinkedListNode(int data)` : constructs a new node, storing the specified data. The `next` node should be set to `null`.
* Accessors:
	* `public int getData()` : return the `int` data stored in the node.
	* `public LinkedListNode getNext()` : return the next node in the list.
* Mutators:
	* `public void setData(int newData)` : change the `data` attribute. It will now store the data that was passed into this method.
	* `public void setNext(LinkedListNode newNext)` : change `next` attribute to reference the argument node.
* Other methods:
	* `public String toString` : returns a string representation of the node. Something like `"Node(<data>)"`, where `<data>` is the integer stored in the node. 

### `LinkedList`

Once your `LinkedListNode` class passes all of the tests in the client, you're ready to create your `LinkedList` class. A skeleton has been provided in [LinkedList.java](./LinkedLists/LinkedList.java), which you'll need to complete. Comments provide direction regarding what each method should do, as well as some tips and reminders. You can test using the provided [LinkedListClient.java](./LinkedLists/LinkedListClient.java). You should implement the methods in the order that the client tests them, and fully debug each one before moving on.

Sample run:

```
*****************************************************************************************
**************************** DEFAULT CONSTRUCTOR + ACCESSORS ****************************
*****************************************************************************************

Running default constructor...
Resulting list:
	List   > List{  }
	Length > 0
	Head   > null
	Tail   > null


*****************************************************************************************
**************************************** APPEND *****************************************
*****************************************************************************************

Appending (1)...
	List   > List{ (1) }
	Length > 1
	Head   > Node(1)
	Tail   > Node(1)

Appending (2)...
	List   > List{ (1) -> (2) }
	Length > 2
	Head   > Node(1)
	Tail   > Node(2)

Appending (3)...
	List   > List{ (1) -> (2) -> (3) }
	Length > 3
	Head   > Node(1)
	Tail   > Node(3)

Appending (4)...
	List   > List{ (1) -> (2) -> (3) -> (4) }
	Length > 4
	Head   > Node(1)
	Tail   > Node(4)

Appending (5)...
	List   > List{ (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 5
	Head   > Node(1)
	Tail   > Node(5)


*****************************************************************************************
********************************* SECONDARY CONSTRUCTOR *********************************
*****************************************************************************************

Testing secondary constructor to with input > { 1 2 3 4 5 }
	List   > List{ (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 5
	Head   > Node(1)
	Tail   > Node(5)


*****************************************************************************************
**************************************** PREPEND ****************************************
*****************************************************************************************

Prepending (0)...
	List   > List{ (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 6
	Head   > Node(0)
	Tail   > Node(5)

Prepending (-1)...
	List   > List{ (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 7
	Head   > Node(-1)
	Tail   > Node(5)

Prepending (-2)...
	List   > List{ (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 8
	Head   > Node(-2)
	Tail   > Node(5)

Prepending (-3)...
	List   > List{ (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 9
	Head   > Node(-3)
	Tail   > Node(5)

Prepending (-4)...
	List   > List{ (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 10
	Head   > Node(-4)
	Tail   > Node(5)

Prepending (-5)...
	List   > List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 11
	Head   > Node(-5)
	Tail   > Node(5)


*****************************************************************************************
****************************************** GET ******************************************
*****************************************************************************************

Getting value at index 0 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (-5)

Getting value at index 1 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (-4)

Getting value at index 2 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (-3)

Getting value at index 3 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (-2)

Getting value at index 4 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (-1)

Getting value at index 5 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (0)

Getting value at index 6 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (1)

Getting value at index 7 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (2)

Getting value at index 8 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (3)

Getting value at index 9 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (4)

Getting value at index 10 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Result > (5)

Getting value at index -1 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
IndexOutOfBoundsException was properly thrown!

Getting value at index 11 of
List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
IndexOutOfBoundsException was properly thrown!

Check that array metadata is still intact:
	List   > List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 11
	Head   > Node(-5)
	Tail   > Node(5)


*****************************************************************************************
**************************************** POPLEFT ****************************************
*****************************************************************************************

Before first pop:
	List   > List{ (-5) -> (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 11
	Head   > Node(-5)
	Tail   > Node(5)

Popping...
	Popped value > (-5)
Remaining list:
	List   > List{ (-4) -> (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 10
	Head   > Node(-4)
	Tail   > Node(5)

Popping...
	Popped value > (-4)
Remaining list:
	List   > List{ (-3) -> (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 9
	Head   > Node(-3)
	Tail   > Node(5)

Popping...
	Popped value > (-3)
Remaining list:
	List   > List{ (-2) -> (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 8
	Head   > Node(-2)
	Tail   > Node(5)

Popping...
	Popped value > (-2)
Remaining list:
	List   > List{ (-1) -> (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 7
	Head   > Node(-1)
	Tail   > Node(5)

Popping...
	Popped value > (-1)
Remaining list:
	List   > List{ (0) -> (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 6
	Head   > Node(0)
	Tail   > Node(5)

Popping...
	Popped value > (0)
Remaining list:
	List   > List{ (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 5
	Head   > Node(1)
	Tail   > Node(5)

Popping...
	Popped value > (1)
Remaining list:
	List   > List{ (2) -> (3) -> (4) -> (5) }
	Length > 4
	Head   > Node(2)
	Tail   > Node(5)

Popping...
	Popped value > (2)
Remaining list:
	List   > List{ (3) -> (4) -> (5) }
	Length > 3
	Head   > Node(3)
	Tail   > Node(5)

Popping...
	Popped value > (3)
Remaining list:
	List   > List{ (4) -> (5) }
	Length > 2
	Head   > Node(4)
	Tail   > Node(5)

Popping...
	Popped value > (4)
Remaining list:
	List   > List{ (5) }
	Length > 1
	Head   > Node(5)
	Tail   > Node(5)

Popping...
	Popped value > (5)
Remaining list:
	List   > List{  }
	Length > 0
	Head   > null
	Tail   > null

Popping...
	Popped value > (null)
Remaining list:
	List   > List{  }
	Length > 0
	Head   > null
	Tail   > null


*****************************************************************************************
************************************** POPATINDEX ***************************************
*****************************************************************************************

Constructing new list from array { 1 2 3 4 5 }...
	List   > List{ (1) -> (2) -> (3) -> (4) -> (5) }
	Length > 5
	Head   > Node(1)
	Tail   > Node(5)

Popping value at index -1 of
List{ (1) -> (2) -> (3) -> (4) -> (5) }
IndexOutOfBoundsException was properly thrown!

Popping value at index 5 of
List{ (1) -> (2) -> (3) -> (4) -> (5) }
IndexOutOfBoundsException was properly thrown!

Popping value at index 4 of
List{ (1) -> (2) -> (3) -> (4) -> (5) }
	Popped value > (5)
Remaining list:
	List   > List{ (1) -> (2) -> (3) -> (4) }
	Length > 4
	Head   > Node(1)
	Tail   > Node(4)

Popping value at index 0 of
List{ (1) -> (2) -> (3) -> (4) }
	Popped value > (1)
Remaining list:
	List   > List{ (2) -> (3) -> (4) }
	Length > 3
	Head   > Node(2)
	Tail   > Node(4)

Popping value at index 1 of
List{ (2) -> (3) -> (4) }
	Popped value > (3)
Remaining list:
	List   > List{ (2) -> (4) }
	Length > 2
	Head   > Node(2)
	Tail   > Node(4)

Popping value at index 0 of
List{ (2) -> (4) }
	Popped value > (2)
Remaining list:
	List   > List{ (4) }
	Length > 1
	Head   > Node(4)
	Tail   > Node(4)

Popping value at index 0 of
List{ (4) }
	Popped value > (4)
Remaining list:
	List   > List{  }
	Length > 0
	Head   > null
	Tail   > null


*****************************************************************************************
************************************* INSERTATINDEX *************************************
*****************************************************************************************

Inserting value 0 at index -1 of
List{  }
IndexOutOfBoundsException was properly thrown!

Inserting value 0 at index 1 of
List{  }
IndexOutOfBoundsException was properly thrown!

Inserting value 3 at index 0 ofList{  }
Resulting list:
	List   > List{ (3) }
	Length > 1
	Head   > Node(3)
	Tail   > Node(3)

Inserting value 1 at index 0 ofList{ (3) }
Resulting list:
	List   > List{ (1) -> (3) }
	Length > 2
	Head   > Node(1)
	Tail   > Node(3)

Inserting value 4 at index 2 ofList{ (1) -> (3) }
Resulting list:
	List   > List{ (1) -> (3) -> (4) }
	Length > 3
	Head   > Node(1)
	Tail   > Node(4)

Inserting value 2 at index 1 ofList{ (1) -> (3) -> (4) }
Resulting list:
	List   > List{ (1) -> (2) -> (3) -> (4) }
	Length > 4
	Head   > Node(1)
	Tail   > Node(4)


*****************************************************************************************
**************************************** TOARRAY ****************************************
*****************************************************************************************

Converting { } to a list and back...
Result > { }

Converting { 1 } to a list and back...
Result > { 1 }

Converting { 1 2 3 4 } to a list and back...
Result > { 1 2 3 4 }


Process finished with exit code 0
```
