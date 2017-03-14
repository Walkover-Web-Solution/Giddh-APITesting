## README

The following is a quick guide to preparing the Giddh APIs Testing.

Prerequisites
-------------

* jdk 1.8
* maven 3.3.3
* TestNG


Following environment variable(s) need to be set:

0. AES_128_KEY
0. GIDDH_PGSQL_ENCRYPTION_PASSWORD
0. SENDGRID_KEY



Usual build tasks
-----------------

* Compile and run test for default active profile
`mvn compile test`

* Compile and run test for different file
`mvn test -P dev`

* Report updates on dependencies
`mvn versions:display-dependency-updates`



Giddh & copy; 2017