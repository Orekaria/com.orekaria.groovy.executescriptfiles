package src.case3

import src.GroovyScriptHelper

class Pipeline {

   Script script

   Pipeline(GroovyTestCase groovyTestCase) {
      script = GroovyScriptHelper.loadScript(groovyTestCase)
   }

   def call(Closure cl) {
      script(cl)
   }

   def call(Map config = [:]) {
      script(config)
   }

   Map getConfig() {
      script.config
   }

   void intercept(String s, Closure closure) {
      script.getBinding().setVariable("echo", { println 'intercepted!' })
   }

}
