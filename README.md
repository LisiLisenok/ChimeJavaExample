
[Chime](https://github.com/LisiLisenok/Chime) is time scheduler which works on [Vert.x](http://vertx.io/).  

_Chime_ is written in [Ceylon](http://ceylon-lang.org/) and to use it with Java _Ceylon verticle factory_
has to be available at class path.  

> Note: Vert.x 3.4.1 depends on Ceylon 1.3.0 while Chime 0.2.0 depends on Ceylon 1.3.2.
  It is required to put versions to consistency.  
  
To build the project with Maven it is required:  
1. Add dependency on **io.vertx:vertx-core:3.4.1** with excluded dependency on **org.ceylon-lang:ceylon-complete**.
   This is general dependency on Vert.x in Java.  
2. Add dependency on **io.vertx:vertx-lang-ceylon:3.4.1** with excluded dependency on **org.ceylon-lang:ceylon-complete**.
   This dependency provides Ceylon verticle factory.  
3. Add dependency on **org.ceylon-lang:ceylon-complete:1.3.2**.
   This is required to use Ceylon.  

As example, see link:pom.xml[pom] in this repository.  

This Chime Java example:  
* Deploys _Chime_.  
* Creates timer with 1 second interval which fires just 3 times.  
* Prints timer event to stdout.  
* Closes Vert.x when timer completes.  