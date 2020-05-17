package pipelines.case2

import src.case2.Pipeline

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class c20_pipelineTest extends GroovyTestCase {

   void test_minimal() {
      def pipeline = new Pipeline(this)
      pipeline()
      assert pipeline.config != null
   }

   void test_Map() {
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

   /**
    * This is a very important test
    * There are several ways to inject and intercept methods in Groovy objects
    * For example, using .metaClass.
    * But testing requires that the objects are completely isolated
    */
   void test_threadSafe() {
      def pipeline = new Pipeline(this)
      def pipeline2 = new Pipeline(this)
      def count = 0
      def count2 = 0
      pipeline.mock('echo', { Object s -> // methods can be intercepted
         println('intercepted!')
         count += 1
         sleep(10)
      })
      pipeline2.mock('echo', { Object s -> // methods can be intercepted
         println('intercepted!')
         count2 += 1
      })

      def threadPool = Executors.newFixedThreadPool(4)
      List<Future> futures = new ArrayList<>()
      futures.add(threadPool.submit({
         pipeline([
            some_key: 'some_value'
         ])
      } as Callable<Integer>))
      futures.add(threadPool.submit({
         pipeline2([
            some_key2: 'some_value2'
         ])
      } as Callable<Integer>))
      futures.each { it.get() }

      assert count == 3
      assert count2 == 3

      assert pipeline.config.some_key == 'some_value'
      assert pipeline2.config.some_key2 == 'some_value2'
      assert pipeline.config.respondsTo('some_key2') == []
      assert pipeline2.config.respondsTo('some_key') == []
   }

   /**
    * Properties cannot be intercepted with .mock
    */
   void test_interceptProperty() {
      def pipeline = new Pipeline(this)
      def count = 0
      pipeline.mock('config', { -> // properties can be intercepted
         println('intercepted!')
         count += 1
      })
      pipeline()
      shouldFail {
         assert count == 3
      }
   }

}
