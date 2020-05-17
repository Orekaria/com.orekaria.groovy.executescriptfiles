package pipelines.case2

import src.case2.Pipeline

class c20_pipelineTest extends GroovyTestCase {

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
      assert pipeline.config.some_key == 'some_value'
   }

   void test3() {
      def pipeline = new Pipeline(this)
      pipeline([
         some_key: 'some_value'
      ])
      assert pipeline.config != null
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.ciPipeline_key == 'some_value'
      assert pipeline.config.levelClassMethodConfigVar
      assert pipeline.config.levelClassMethodClosureInConfigVar
   }

   void test_interceptMethod() {
      def pipeline = new Pipeline(this)
      def count = 0
      pipeline.mock('echo', { Object s -> // methods can be intercepted
         println('intercepted!')
         count += 1
      })
      pipeline()
      assert count == 3
   }

}
