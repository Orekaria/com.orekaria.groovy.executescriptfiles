package src.case01

import src.GroovyScriptHelper

class PipelineMock {

   Script pipeline

   PipelineMock(GroovyTestCase groovyTestCase) {
      pipeline = GroovyScriptHelper.loadScript(groovyTestCase)
   }

}
