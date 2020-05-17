package src.case2

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
      if (script.c21_ciPipeline != null) {
         return script.c21_ciPipeline.config
      }
      script.config
   }

   void intercept(String s, Closure closure) {
      script.getBinding().setVariable("echo", { println 'intercepted!' })
   }

}
