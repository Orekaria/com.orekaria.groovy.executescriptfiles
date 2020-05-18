package src.case2

import src.GroovyScriptHelper

class Pipeline {

   Script script

   Pipeline(GroovyTestCase groovyTestCase) {
      script = GroovyScriptHelper.loadScript(groovyTestCase)
      script.binding.variables.env = [
         WORKSPACE: '/srv/ci/workspace'
      ]
   }

   def call(Closure cl) {
      script(cl)
   }

   def call(Map config = [:]) {
      script(config)
   }

   Map getConfig() {
      return script.c21_ciPipeline.config
   }

   void mock(String name, Closure cl) {
      script.binding.setVariable(name, { cl() })
   }

}
