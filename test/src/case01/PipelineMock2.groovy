package src.case01

import src.GroovyScriptHelper

class PipelineMock2 {

   static Script getMock(GroovyTestCase groovyTestCase) {
      return GroovyScriptHelper.loadScript(groovyTestCase)
   }

}
