# PetaExpress API Specs

<span style="display: inline-block">
[![Build Status]()]()
[![License](http://img.shields.io/badge/license-apache%20v2-blue.svg)](https://github.com/peta-express/cloud-api-specs/blob/master/LICENSE)
</span>

Specifications of PetaExpress APIs.

## Specification

In PetaExpress, we use [OpenAPI Specification (Swagger) v2.0](http://swagger.io) to describe our APIs, and this API specifications can be used to generate code for SDKs and generate documentations.

A customized data type was added to the original OpenAPI Specification v2.0 standard for better describing our APIs.

View [the OpenAPI Specification (Swagger) v2.0 schema](./api_spec_schema_swagger_v2.0.json).

View [an API specification example](./api_spec_example_swagger_v2.0.json) using this schema.

#### Customized Data Types

| Common Name | [`type`](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md#dataTypeType) | [`format`](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md#dataTypeFormat) | Comments |
|--------------------|----------|--------------------|--------------------------------------------|
| DateTime (RFC 822) | `string` | `date-time-rfc822` | Example: `Mon, 02 Jan 2006 15:04:05 GMT` |

Refer to [___data types___](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md#data-types) in OpenAPI Specification (Swagger) v2.0 for more information.

### Directory Organization

The structure of the directories has following levels:

1. Name of Service
2. API Version
3. Specification Format
4. the Specification

#### For Example:

``` bash
└── 2013-08-30
    └── swagger
        ├── api_v2.0.json
        ├── definitions.json
        ├── instance.json
        ├── volume.json
        ...
```

Currently, the specifications are expected to be in the OpenAPI Specification (Swagger) v2.0's JSON format.

## Validation

There is a lot of tools to validate JSON file with its JSON schema, we chose [`z-schema`](https://github.com/zaggino/z-schema) to do this.

___Notice:___ _[NodeJS](https://nodejs.org/en/) is required._

Get in the project directory and install dependencies.

``` bash
$ npm install
```

Run the validate script.

``` bash
$ npm test # or `npm t`
```

## Reference Documentations

- [OpenAPI Specification (Swagger)](http://swagger.io)
- [JSON Schema](http://json-schema.org)

## Contributing

1. Fork it ( https://github.com/peta-express/cloud-api-specs/fork )
2. Create your feature branch (`git checkout -b new-feature`)
3. Commit your changes (`git commit -asm 'Add some feature'`)
4. Push to the branch (`git push origin new-feature`)
5. Create a new Pull Request

## LICENSE

[The Apache License (Version 2.0, January 2004)](http://www.apache.org/licenses/LICENSE-2.0.html).
