Simple C24 iO, Spring Integration & Spring Boot example
====================================================

This example shows how C24 iO can be used to parse, validate, filter and persist messages as part
of a Spring Integration flow. For a more complete example of using iO & Spring Integration (as 
well as Spring Batch) please see the [C24 DataLoader](https://github.com/C24-Technologies/c24-sample-dataloader)


Overview
--------

The input files are fictitious (& incomplete) lists of trades, one per line in CSV format.
The 2 files (in the data directory) are identical except for one small difference which
will cause one fail to fail validation against a custom rule (the quantity must be greater
than zero).

The flow is:

* Acquire file using an inbound file adapter
* Parse the file using iO's unmarshalling transformer
* Semantically validate the parsed objects
* Audit successfully validated files
* Split the file into its constituent trades
* Persist each trade into H2

In the event of an error or parsing/validation failure an appropriate record will also be 
written to H2.

The DataLoader.java file starts up the Spring context (which also starts an embedded H2 instance) and
provides a very simple command line interface for running queries against H2.


Running the Example
-------------------

The provided Maven POM contains the necessary logic to build and run the application. The application is
built with:

    mvn compile

and run with:

    mvn spring-boot:run

Once the app is running messages will have been read in, transformed and persisted to an in-memory DB. To view them open a browser
  and navigate to http://localhost:8080:

On completion the table should contain 8 rows.


Commentary
----------

This is a minimalist example and, for example, does not:

* Implement a robust error handling policy. Far richer information is available to help diagnose faults
  and storing everything in the same table is clearly suboptimal. Again, the [C24 DataLoader]
  (https://github.com/C24-Technologies/c24-sample-dataloader) is more comprehensive in this regard.

* Demonstrate other relational persistence approaches, for example iO can automatically generate
  Hibernate mappings for all generated types.
  
* Provide monitoring for the Spring Integration flow. The [C24 DataLoader](https://github.com/C24-Technologies/c24-sample-dataloader)
  shows how JMX can be used to accomplish this.

