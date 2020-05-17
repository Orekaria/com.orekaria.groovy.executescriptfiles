package pipelines.case03

import src.case03.Pipeline

class x030_pipelineTest extends GroovyTestCase {

   void test() {
      def pipeline = new Pipeline(this)
      pipeline()
      assert pipeline.config != null
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
      assert pipeline.config.some_key == 'some_value'
      assert pipeline.config.targetLabel == 'master'
      assert pipeline.config.actionLevel == 4
      assert pipeline.config.dryRun == true
   }

}
