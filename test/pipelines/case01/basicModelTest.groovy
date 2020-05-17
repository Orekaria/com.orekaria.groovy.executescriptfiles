package pipelines.case01

class basicModelTest extends GroovyTestCase {

   void test_me() {
      def echoOutput = new LinkedList()
      def pipeline = loadPipelineScriptForTest("minimalPipeline.groovy")
      def pipeline2 = loadPipelineScriptForTest("ciPipeline.groovy")
      def pipeline3 = loadPipelineScriptForTest("yetAnotherLevel.groovy")

      pipeline.getBinding().setVariable("echo", { echoOutput.offer(it) })
      pipeline.getBinding().setVariable("ciPipeline", pipeline2)
      pipeline2.getBinding().setVariable("echo", { echoOutput.offer(it) })
      pipeline2.getBinding().setVariable("yetAnotherLevel", pipeline3)
      pipeline3.getBinding().setVariable("step", { String s, Closure cl -> println(s) })

      pipeline()

      echoOutput.each {
         println it
      }
      assert echoOutput.size() == 5
   }

   Script loadPipelineScriptForTest(String _path) {
      String[] path_parts = _path.split("/")
      String filename = path_parts[path_parts.length - 1]
      String resource_path = "/"
      if (path_parts.length >= 2) {
         resource_path = String.join("/", path_parts[0..path_parts.length - 2])
         resource_path = "/${resource_path}/"
      }
      println resource_path
      println filename
      String[] gsc = generateScriptClasspath(resource_path)
      println gsc

      GroovyScriptEngine script_engine = new GroovyScriptEngine('vars/')
      Class<Script> script_class = script_engine.loadScriptByName("${filename}")

      Script script = script_class.newInstance()

      return script
   }

   String[] generateScriptClasspath(String resourcePath) {
      return script_class_path.collect { path -> path + resourcePath }
   }

   String[] script_class_path = [
      "vars",               // if it's a main resource
   ]

}
