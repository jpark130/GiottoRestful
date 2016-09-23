# GiottoRestful
# GiottoActiveMapService

This module allows clients to query/post/delete People/Location/GeneralType thing
to Active Map Service for Giotto.

## Concept

* RESTful requires explicitly declaring the exposed API. When enabling
the module, nothing happens until a plugin declares it.
* The exposed properties need to be explicitly declared. Clients will need to query to the appropriate database.


## Starting Restful Service
Clients can simply start the restful service on local host by running com.giotto.web.App.java.
Doing this, clients can start a restful server to query to the remote Mongodb.


###Example Code
If you're trying to access data in a specific database, you must query to 
http://localhost:1111/"name_of_the_database"
Below are the example query code for each of the cases.

####restful\_custom/restful\_custom.info
```ini
name = RESTful custom
description = Custom RESTful resource.
core = 7.x
dependencies[] = restful

registry_autoload[] = PSR-4
```

