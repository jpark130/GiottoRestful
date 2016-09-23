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
Below are the example json data to pass on for each of the cases.

####localhost:1111/Location/find
```ini
name = "NSH_3201"
neighbors = ["NSH_3202"]
containment = ["NSH_3"]
```

####localhost:1111/Location/post
```ini
name = "NSH_3201"
neighbors = ["NSH_3202"]
containment = ["NSH_3"]
```


####localhost:1111/Location/delete
```ini
name = "NSH_3201"
neighbors = ["NSH_3202"]
containment = ["NSH_3"]
```


####localhost:1111/People/find
```ini
name = Tyler 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Major" = "Computer Science"
         "Year" = "Senior"
         }
```


####localhost:1111/People/post
```ini
name = Tyler 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Major" = "Computer Science"
         "Year" = "Senior"
         }
```



####localhost:1111/People/delete
```ini
name = Tyler 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Major" = "Computer Science"
         "Year" = "Senior"
         }
```

