# gist-test

A Simple Java Application
==========================

A simple Application that has one main method that fetches the gist URL provided,
establish URL connection, read lines and does a standard output. However, Unit test
folders for this simple App actually tests the log published in the gist provided.
 - Read a gist and then get ERROR, WARNING and INFO counts.

This is a maven project, the main pom.xml is located on the project root. The Application
code resides in `https://github.com/praveenst/gist-test/tree/master/src`

Jenkins  pipeline has been created to execute the job and the *Jenkinsfile* is also located
in the project root. The pipeline stages are the following:

  * **Preparation**
  > stage('Preparation')  - sets up maven and git repository parameters to pull the sources
  * **Test**
  > stage('Test') - builds and tests the source code from github.
  * **Results**
  > stage('Results') - has the junit reports that can also be accessed from jenkins by clicking
  build number and *Test Result* link.

>Notes:

> 1) The pipeline assumes that Maven named as 'M3' (Maven tool) must be configured
in global tool configuration and maven home is defined.  
> 2) https://gist.githubusercontent.com/frnkdny/6ce32d992ec6576548e29312e08fb28b/raw/37252020df292befa7eb99a64d63111cc85da49e/test.log is consumed by the test itself and by executing the test, it is implicitly consumed.
