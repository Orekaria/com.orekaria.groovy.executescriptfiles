Introduction
============

Contains logic to load .groovy files and execute them; allowing replacing methods, catching missing methods, referencing each other and passing parameters up and down, etc.

Only handles Groovy scripts

Note: the structure of the directories is add-hoc (ala Jenkins shared pipelines style), but other structures will work

Quickstart
==========

You may start running all tests, and checking which ones you are interested on. Then, look at the ...Test class that runs it, then at the scripts it loads

command line
------------

- clone the repo
- run gradlew test

IntelliJ
--------

- open the project
- in the gradle tab -> Tasks -> verification, execute :code:`test`

Code explained
==============

The project is divided in 3 groups of components: scripts (in :code:`vars`), script wrappers (in :code:`test/src`) and tests (in :code:`test/pipelines`)

And organized in case 0, 1, 2, 3

- case 0: with only the :code:`standaloneTest`, that can run by its own

- case 1: some random approaches

- case 2: gives more weight to the deeper script. :code:`c21_ciPipeline` in this case

   this is the approach that I will finally implement in a Jenkins Shared Library project

   it is thread safe and includes a thread safe test

- case 3: gives more weight to the main script. :code:`c30_pipeline` in this case

GroovyScriptHelper
------------------

This is the class that:

- loads the script to be run
- loads all the scripts in the :code:`vars` folder and adds references to them in the script to run
- injects new variables to the script
- redirects missing method and properties to the caller; overriding .metaClass.methodMissing and .metaClass.propertyMissing

The method that loads the Groovy script:

.. code-block:: groovy

   private static Script loadScriptFromVars(String filename) {
      GroovyScriptEngine gse = new GroovyScriptEngine('vars/')
      Class<Script> scriptClass = gse.loadScriptByName("${filename}.groovy")
      Script script = scriptClass.newInstance()
      return script
   }

The method that injects new methods to the Groovy script:

.. code-block:: groovy

   private static void injectMocks(Script script) {
      script.getBinding().with {
         setVariable("echo", { println it })
         setVariable("step", { String s, Closure cl ->
            println "inside step ${s}"
            cl.call(s)
         })
         ...
      }
   }

Thanks to the `https://github.com/ExpediaGroup/jenkins-spock` project for insights on how to better load a Groovy script
