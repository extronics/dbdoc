dbdoc
=====

dbdoc works similar to code documentation tools like javadoc: it
analyzes the database schema structure and extracts additional
comments to create documentation files.

It is designed to be run by automated build processes and therefore
comes with a simple command line interface.

The modular API allows for support of different dbms and output
formats. Currently, MySQL databases and HTML are supported.

Documentation for databases with limited support for schema comments
(e.g. MySQL) can be enhanced with additional comments from simple text
files.

Usage
-----

	./dbdoc <options>

	Options:
	    --importer    Importer type, like 'mysql'. Specifies the type of database
	                  to be documented.
	    --exporter    Exporter type, like 'html'. Specifies the documentation
	                  format.
	    --mod         Allows to add modifiers which can add or change information.
	    --text-dir    Specifies a directory from which additional documentation is
	                  read.
	
	Importers:
	    mysql            Imports documentation from a mysql database.
	    --mysql-host     The database host.
	    --mysql-port     The mysql port, defaults to 3306.
	    --mysql-db       The database name.
	    --mysql-username The database user, defaults to 'root'.
	    --mysql-password The database password, defaults to ''.
	    The modifier 'mysql-ifk' recognizes columns named like <tablename>_<colname>
	    as a reference to the table and column and adds an foreign key to the docs.
	
	Exporters:
	    html          Exporters the documentation in html format.
	    --html-dir    The directory to which the documentation files are written.

### Examples

Building the test documentation to /var/www/

	$ ./dbdoc --importer mysql --exporter html --mysql-host localhost --mysql-db doctest --mysql-username doctest --html-dir /var/www/