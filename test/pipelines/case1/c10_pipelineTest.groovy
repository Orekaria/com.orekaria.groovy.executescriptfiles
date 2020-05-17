package pipelines.case1

import src.case1.Pipeline
import src.case1.PipelineMock
import src.case1.PipelineMock2

class c10_pipelineTest extends GroovyTestCase {

   void test() {
      def pipeline = new PipelineMock(this).pipeline
      pipeline()
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.isSuccess == true
   }

   void test2() {
      def pipelineMock = new PipelineMock(this)
      pipelineMock.pipeline.call()
      assert pipelineMock.pipeline.config.some_key == 'some_value'
      assert pipelineMock.pipeline.config.isSuccess == true
   }

   void test3() {
      def pipeline = PipelineMock2.getMock(this)
      pipeline()
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.isSuccess == true
   }

   void test4() {
      def pipeline = new Pipeline(this).script
      pipeline.getBinding().setVariable("echo", { println 'intercepted!' })
      pipeline()
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.isSuccess == true
   }

   void test5() {
      def pipeline = new Pipeline(this)
      pipeline.intercept("echo", { println 'intercepted!' })
      pipeline()
      assert pipeline.script.config.some_key == 'some_value'
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.script.config.isSuccess == true
   }

   void test6() {
      def pipeline = new Pipeline(this)
      pipeline()
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.isSuccess == true
   }

}
