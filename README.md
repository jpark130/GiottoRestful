# GiottoRestful

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

####localhost:1111/location/find
```ini
{
name = "NSH_3201"
neighbors = ["NSH_3202"]
containment = ["NSH_3"]
}
```

####localhost:1111/location/post
```ini
{
name = "NSH_3201"
neighbors = ["NSH_3202"]
containment = ["NSH_3"]
}
```


####localhost:1111/location/delete
```ini
{
name = "NSH_3201"
neighbors = ["NSH_3202"]
containment = ["NSH_3"]
}
```

####localhost:1111/people/find
```ini
{
name = Tyler 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Major" = "Computer Science"
         "Year" = "Senior"
         }
}
```


####localhost:1111/people/post
```ini
{
name = Tyler 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Major" = "Computer Science"
         "Year" = "Senior"
         }
}
```


####localhost:1111/people/delete
```ini
{
name = Tyler 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Major" = "Computer Science"
         "Year" = "Senior"
         }
}
```


####localhost:1111/thing/find
```ini
{
name = "Coffee Maker" 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Owner" = "Tyler"
         "Status" = "Broken"
         }
}
```


####localhost:1111/thing/post
```ini
{
name = "Coffee Maker" 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Owner" = "Tyler"
         "Status" = "Broken"
         }
}
```


####localhost:1111/thing/delete
```ini
{
name = "Coffee Maker" 
location = {name = "NSH_3201"
            neighbors = ["NSH_3202"]
            containment = ["NSH_3"]
            }
other = {"Owner" = "Tyler"
         "Status" = "Broken"
         }
}
```

