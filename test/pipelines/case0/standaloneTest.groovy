package pipelines.case0

class standaloneTest extends GroovyTestCase {

   void test_me() {
      def echoOutput = new LinkedList()
      def pipeline = loadPipelineScriptForTest("c10_pipeline.groovy")
      def pipeline2 = loadPipelineScriptForTest("c11_ciPipeline.groovy")
      def pipeline3 = loadPipelineScriptForTest("c12_yetAnotherLevel.groovy")

      pipeline.getBinding().setVariable("echo", { echoOutput.offer(it) })
      pipeline.getBinding().setVariable("c11_ciPipeline", pipeline2)
      pipeline2.getBinding().setVariable("echo", { echoOutput.offer(it) })
      pipeline2.getBinding().setVariable("c12_yetAnotherLevel", pipeline3)
      pipeline3.getBinding().setVariable("step", { String s, Closure cl -> println(s) })

      pipeline()

      echoOutput.each {
         println it
      }
      assert echoOutput.size() == 5
   }

   Script loadPipelineScriptForTest(String _path) {
      String[] path_parts = _path.split("/")
      String filename = path_parts.last()
      String resource_path = "/"
      if (path_parts.length >= 2) {
         resource_path = String.join("/", path_parts[0..path_parts.length - 2])
         resource_path = "/${resource_path}/"
      }
      println resource_path
      println filename
      String[] gsc = generateScriptClasspath(resource_path)
      println gsc

      GroovyScriptEngine gse = new GroovyScriptEngine('vars/')
      Class<Script> scriptClass = gse.loadScriptByName("${filename}")

      Script script = scriptClass.newInstance()

      return script
   }

   String[] generateScriptClasspath(String resourcePath) {
      return scriptClassPath.collect { path -> path + resourcePath }
   }

   String[] scriptClassPath = [
      "vars",               // if it's a main resource
   ]

}
