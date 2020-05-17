def call(Closure cl) {
   println "  execute the closure"
   def content = {}
   if (cl != null) {
      cl.delegate = content // collect in this object. The type of the object determines which content is collected, e.g. to collect variables, change the type to [:]
      cl.resolveStrategy = Closure.DELEGATE_FIRST
      cl(this) // execute the closure before running this script and collect variables, etc. 'this' is passed so the caller have access to properties, methods, etc of this object
   }

   println "  after the closure has been executed:"
   println "${content.toString()}"
   println '___'
   assert this.respondsTo('levelClassVar') == []
   assert levelClassMethodVar
   assert this.respondsTo('levelClassMethodNotTransferredVar') == []
   assert levelClassMethodTransferredVar
   assert this.respondsTo('levelClassMethodClosureVar') == []
   assert content.config.levelClassMethodClosureInConfigVar
   // once the initialization has been done and all variables and methods have been injected, use the config as source of configuration
   // note that every other variable definition will be available but ont
   call(content.config)
}

def call(Map inConfig = [:]) {
   println "  [start of c21_ciPipeline]"

   // verify values injected by parent
   assert levelClassMethodTransferredVar
   assert this.respondsTo('levelClassMethodNotTransferredVar') == []
   assert targetLabel == 'master'

   config = inConfig
   assert config.levelClassMethodClosureInConfigVar
   config.ciPipeline_key = 'some_value' // assign new value

   step('ci21_ciPipeline') {
   }

   (1..3).each {
      echo it
   }

   println "config = '${config}'"
   println "  [end of c21_ciPipeline]"
}
