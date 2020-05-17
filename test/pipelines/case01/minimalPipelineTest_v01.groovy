package pipelines.case01

import src.GroovyScriptHelper

class minimalPipelineTest_v01 extends GroovyTestCase {

   void test_interceptEcho() {
      def echoOutput = new LinkedList()
      def pipeline = GroovyScriptHelper.loadScript(this)
      pipeline.getBinding().setVariable("echo", {
         echoOutput << it
         println 'intercepted!'
      })
      pipeline()
      assert echoOutput.size() == 5
   }

   void test_minimal() {
      def pipeline = GroovyScriptHelper.loadScript("minimalPipeline")
      pipeline()
      assert pipeline.ciPipeline.isSuccess == true
   }

   void test_getTasks() {
      def pipeline = GroovyScriptHelper.loadScript(this)
      pipeline()
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.isSuccess == true
   }

}
