# TMForum OpenAPI Documentation
The software stack for the TMForum OpenAPI reference implementation is a standard J2EE EJB3 implementation comprised of:

* JPA 2.0 for persistence via EclipseLink
* JAX-RS 2.0 implementation via Jersey for RESTful services
* Jackson for processing JSON
* [AntPathFilter](https://github.com/Antibrumm/jackson-antpathfilter) for filtering JSON objects 
* MySQL or Apache Derby database (configurable)
* Maven for build
* Glassfish application server 4.0 or greater
* Git and GitHub for configuration control
* JElastic runtime environment (PaaS)

The artifact is a standard **.war** file that is deployed on the **Glassfish** server.

The software architecture is layered, to address separation of concerns.
* **Models** are standard JPA annotated POJOs that get deployed as RDBMS tables.
  > Models will **_always_** be implemented by developers on a per API basis.
  > Models are further annotated with Jackson annotations, to serialize/deserialize date fields. Date and datetime fields are stored in a custom format.

* **Facades** manage the EJB EntityManager, handle the transaction lifecycle and validate input data.
  > **AbstractFacade** is a complex but _boiler plate_ implementation that **_should not_** be changed by developers.
  > Other resource specific facades should always extend AbstractFacade and will be implemented by developers on a per resource basis.

* **Resources** are standard JAX-RS Jersey implementations and provide REST services
  > The Resource path along with any path params, **_must_** fully align with the Swagger documentation and API specification for each API and the respective resources and verbs.
  > The services consume and produce JSON and invoke Facades, to validate, retrieve and store data.
  
* **Common Utilities** support the implementation by providing utility functions that may be invoked by the Facade and Resource layers.
  > The custom datatime handler, Jackson property filter, Jackson deserializer and other such utilities feature in this package.
  > Most of these utilities are _boiler plate_ and should not need **any** modifications.
  > You may however add your own API/project specific utilities to this package.

* **ApplicationConfig.java** is a standard Jersey approach to registering packages that manage resources.
  > Barring _boiler plate_ packages, you **must** register your resource packages here.
  > **Note:** We create and register a custom JacksonFeature class, due to a known Glassfish bug. **DO NOT** change or remove this implementation.

**persistence.xml**, **glassfish-web.xml** and **glassfish-resources.xml** are standard but key configuration files that manage the entire application settings. You **will** have to modify these settings, based on the type of database you've chosen (MySQL or Apache Derby), login credentials for the database, applcation context path etc.
  
All other files are _boiler plate_ implementations for parsing URLs, handling custom exceptions, security and standard configuration files. These should be **no** need to modify any of these files.
