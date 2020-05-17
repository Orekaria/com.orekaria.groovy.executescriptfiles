package pipelines.case02

import src.case02.Pipeline

class x020_pipelineTest extends GroovyTestCase {

   void test() {
      def pipeline = new Pipeline(this)
      pipeline()
      assert pipeline.config != null
   }

   void test2() {
      def pipeline = new Pipeline(this)
      pipeline([
         some_key: 'some_value'
      ])
      assert pipeline.config != null
      assert pipeline.config.callerConfig.some_key == 'some_value'
   }

   void test3() {
      def pipeline = new Pipeline(this)
      pipeline([
         some_key: 'some_value'
      ])
      assert pipeline.config != null
      pipeline.config.each {
         println(it)
      }
      assert pipeline.config.targetLabel == 'master'
   }

}