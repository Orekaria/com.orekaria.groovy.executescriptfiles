package pipelines.case4

class c40_pipelineTest extends GroovyTestCase {

   void testScripted() {
      Script script = src.GroovyScriptHelper.loadScriptFromVars('c40_scriptedPipeline')
      script.metaClass.methodMissing = { String name, args->
         println "Intercepted ${name}"
      }
      script()
   }

   void testDeclarative() {
      Script script = src.GroovyScriptHelper.loadScriptFromVars('c40_declarativePipeline')
      script.metaClass.methodMissing = { String name, args->
         println "Intercepted ${name}"
      }
      script()
   }

}
