package src.case1

import src.GroovyScriptHelper

class PipelineMock {

   Script pipeline

   PipelineMock(GroovyTestCase groovyTestCase) {
      pipeline = GroovyScriptHelper.loadScript(groovyTestCase)
   }

}
