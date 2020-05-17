package pipelines.case1

import src.GroovyScriptHelper

class c10_pipelineTest_0 extends GroovyTestCase {

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
      def pipeline = GroovyScriptHelper.loadScript("c10_pipeline")
      pipeline()
      assert pipeline.c11_ciPipeline.isSuccess == true
   }

   void test_getTasks() {
      def pipeline = GroovyScriptHelper.loadScript(this)
      pipeline()
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.isSuccess == true
   }

}
