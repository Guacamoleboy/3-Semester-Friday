# What is Function<T, R> and how is it being used?

> This is a note for myself. Context, wording and other things will be for myself. If there are things you do not
> understand..\
> **That** is the reason. It's a note sheet for myself in order to evolve as a developer.

___

# Usage

In the CRUDController classMapper is refeered to as a Function<T, Object>.\
Since the class itself is generic and takes <T> as param that is the first value.\
The last value is Object - which means it can return any class that inherits the Object type.\
Such as DTOs, Strings, UUIDs and so on. Could probably use <R> R but I don't see any reason as to why

So the overall design of the Function<T, R> could be explained like this...

```java
Function<Input, Output>
```

Which would simply look like this in our case...

```java
Function<T, Object>
```

___

# Why not just create an interface?

An interface is useful for certain things. For example in my DAO layer I've added an IDAO interface which acts as
a contract for the EntityManagerDAO which handles all the CRUD methods. This is a contract for the data layer.\

Why not for the Controllers too? The Controllers could probably take an interface too, but since the controllers
only handle HTTP requests / responses which links directly to service which links directly to DAO there's really
no use for it.

While I do understand the point of an interface and the general idea of an abstract class I feel like there's no need
for an interface for this specific part of the code. 

Correct me if I'm wrong. Here to learn in the end of the day.

___

# CRUDController super call explaining

```java
public class UserController extends CRUDController<User> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public UserController(EntityManagerService<User> service) {
        super(service, User.class, UserResponseMapper::toDTO);
    }

}
```

In this case the **Function<T, R>** would become **Function<User, Object>**.\
**<T>** is the CRUDController<**T**> which is **CRUDController<User>**

The R in T,R - which is the output - is the toDTO return from our UserResponseMapper::toDTO. So whatever that\
method in UserResponseMapper returns is the output type - R - Object.

So as for writing the final function it would be...\

```java
Function<User, Object>
```

As the UserResponseMapper::toDTO maps to a DTO it would look like\

```java
Function<User, UserResponseDTO>
```

That is the final form in this example. So Object is basically just the return type.

___

# Function<T, R> methods in use under CRUDController

```java
.apply(T t)
```

In this specific CRUDController the .apply(T t) function executes the UserResponseMapper::toDTO on the T t\
which is an entity called User. So it applies toDTO on the User entity. In order to keep it generic for all other\
controllers we simply define it as T t instead of hardcoding the entity.

___

# Useful links

1) https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html#:~:text=Interface%20Function&text=Represents%20a%20function%20that%20accepts,method%20is%20apply(Object)%20.
2) https://medium.com/java-today/function-t-r-in-java-ca60c9f120e3
3) https://www.baeldung.com/java-8-functional-interfaces

___

> Created by: Guacamoleboy
>
> Last edited: 05/04-2026