# GiottoRestful
# GiottoActiveMapService

This module allows clients to query/post/delete People/Location/GeneralType thing
to Active Map Service for Giotto.

## Concept

* RESTful requires explicitly declaring the exposed API. When enabling
the module, nothing happens until a plugin declares it.
* The exposed properties need to be explicitly declared. Clients will need to query to the appropriate database.

####restful\_custom/restful\_custom.info
```ini
name = RESTful custom
description = Custom RESTful resource.
core = 7.x
dependencies[] = restful

registry_autoload[] = PSR-4
```

####restful\_custom/src/Plugin/resource/Custom__1_0.php
```php

namespace Drupal\restful_custom\Plugin\resource;

/**
 * Class Custom__1_0
 * @package Drupal\restful_custom\Plugin\resource
 *
 * @Resource(
 *   name = "custom:1.0",
 *   resource = "custom",
 *   label = "Custom",
 *   description = "My custom resource!",
 *   authenticationTypes = TRUE,
 *   authenticationOptional = TRUE,
 *   dataProvider = {
 *     "entityType": "node",
 *     "bundles": {
 *       "article"
 *     },
 *   },
 *   majorVersion = 1,
 *   minorVersion = 0
 * )
 */
class Custom__1_0 extends ResourceEntity implements ResourceInterface {

  /**
   * Overrides EntityNode::publicFields().
   */
  public function publicFields() {
    $public_fields = parent::publicFields();

    $public_fields['body'] = array(
      'property' => 'body',
      'sub_property' => 'value',
    );

    return $public_fields;
  }
}
```

After declaring this plugin, the resource could be accessed at its root URL,
which would be `http://example.com/api/v1.0/custom`.

### Security, caching, output, and customization

See the [Defining a RESTful Plugin](./docs/plugin.md) document for more details.


## Using your API from within Drupal

The following examples use the _articles_ resource from the _restful\_example_
module.

#### Getting a specific version of a RESTful handler for a resource

```php
// Get handler v1.1
$handler = restful()->getResourceManager()->getPlugin('articles:1.1');
```

#### Create and update an entity
```php
$handler = restful()
  ->getResourceManager()
  ->getPlugin('articles:1.0');
// POST method, to create.
$result = restful()
  ->getFormatterManager()
  ->format($handler->doPost(array('label' => 'example title')));
$id = $result['id'];

// PATCH method to update only the title.
$request['label'] = 'new title';
restful()
  ->getFormatterManager()
  ->format($handler->doPatch($id, $request));
```

#### List entities
```php
$handler = restful()->getResourceManager()->getPlugin('articles:1.0');
$handler->setRequest(Request::create(''));
$result = restful()->getFormatterManager()->format($handler->process(), 'json');

// Output:
array(
  'data' => array(
    array(
      'id' => 1,
      'label' => 'example title',
      'self' => 'https://example.com/node/1',
    );
    array(
      'id' => 2,
      'label' => 'another title',
      'self' => 'https://example.com/node/2',
    );
  ),
);
```

### Sort, Filter, Range, and Sub Requests
See the [Using your API within drupal](./docs/api_drupal.md) documentation for
more details.

## Consuming your API
The following examples use the _articles_ resource from the _restful\_example_
module.

#### Consuming specific versions of your API
```shell
# Handler v1.0
curl https://example.com/api/articles/1 \
  -H "X-API-Version: v1.0"
# or
curl https://example.com/api/v1.0/articles/1

# Handler v1.1
curl https://example.com/api/articles/1 \
  -H "X-API-Version: v1.1"
# or
curl https://example.com/api/v1.1/articles/1
```


#### View multiple articles at once
```shell
# Handler v1.1
curl https://example.com/api/articles/1,2 \
  -H "X-API-Version: v1.1"
```


#### Returning autocomplete results
```shell
curl https://example.com/api/articles?autocomplete[string]=mystring
```


#### URL Query strings, HTTP headers, and HTTP requests
See the [Consuming Your API](./docs/api_url.md) document for more details.

## CORS
RESTful provides support for preflight requests (see the
[Wikipedia example](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing#Preflight_example)
for more details).

To configure the allowed domains, you can:

  - Go to `admin/config/services/restful` and set _CORS Preflight_ to the
allowed domain. This will apply globally unless overridden with the method
below.
  - Set the `allowOrigin` key in your resource definition (in the annotation)
to the allowed domain. This setting will only apply to this resource.

Bear in mind that this check is only performed to the top-level resource.
If you are composing resources with competing `allowOrigin` settings, the
top-level resource will be applied.

## Documenting your API
Clients can access documentation about a resource by making an `OPTIONS` HTTP
request to its root URL. The resource will respond with the field information
in the body, and the information about the available output formats and the
permitted HTTP methods will be contained in the headers.


### Automatic documentation
If your resource is an entity, then it will be partially self-documented,
without you needing to do anything else. This information is automatically
derived from the Entity API and Field API.

Here is a snippet from a typical JSON response using only the automatic
documentation:

```json
{
  "myfield": {
    "info": {
      "label": "My Field",
      "description": "A field within my resource."
    },
    "data": {
      "type": "string",
      "read_only": false,
      "cardinality": 1,
      "required": false
    },
    "form_element": {
      "type": "textfield",
      "default_value": "",
      "placeholder": "",
      "size": 255,
      "allowed_values": null
    }
  }
  // { ... other fields would follow ... }
}
```

Each field you've defined in `publicFields` will output an object similar
to the one listed above.


### Manual documentation
In addition to the automatic documentation provided to you out of the box, you
have the ability to manually document your resources.  See the [Documenting your API](./docs/documentation.md)
documentation for more details.


## Modules integration
* [Entity validator 2.x](https://www.drupal.org/project/entity_validator): Integrate
with a robust entity validation (RESTful 1.x requires Entity Validator 1.x).


## Credits
* [Gizra](http://gizra.com)
* [Mateu Aguiló Bosch](https://github.com/e0ipso)
