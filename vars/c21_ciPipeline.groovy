import groovy.transform.Field

@Field
Map config = [:] // required for testing

@Field
boolean catchErrors = true// required for testing

def call(Closure cl) {
   // evaluate the body configuration block, and collect configuration within the object
   if (cl != null) {
      cl.resolveStrategy = Closure.DELEGATE_FIRST
      cl.delegate = config
      cl(this)
   }
   call(config)
}

def call(Map inConfig = [:]) {
   config = inConfig

   (1..3).each {
      echo it
   }
   echo "catchErrors = '${catchErrors}'"
}
