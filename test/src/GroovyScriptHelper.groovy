package src

import org.apache.commons.io.FilenameUtils

class GroovyScriptHelper {

   static loadScript(GroovyTestCase groovyTestCase) {
      def className = groovyTestCase.class.simpleName
      def varsFilename = className.substring(0, className.indexOf('Test'))
      loadScript(varsFilename)
   }

   static loadScript(String filename) {
      // load a script
      def script = loadScriptFromVars(filename)

      // add methods required to fully execute a script
      attachMocks(script)

      // bind the rest of the scripts in the 'vars' directory, to the script
      def dir = new File('vars')
      println "load all objects in the '${dir.canonicalPath}' directory and bind them"
      dir.eachFile {
         def objectName = FilenameUtils.removeExtension(it.name)
         println "bind ${objectName}"
         def scriptToBind = loadScriptFromVars(objectName)
         // redirect all unknown methods to the parent
         scriptToBind.metaClass.methodMissing = { String name, args ->
            // println "missing method '${name}' passed to caller"
            script.invokeMethod(name, args)
         }
         // redirect all unknown properties to the parent
         scriptToBind.metaClass.propertyMissing = { String name ->
            // println "missing property '${name}' passed to caller"
            script[name]
         }
         // create a bind to the parent so the parent can reference it
         script.getBinding().setVariable(objectName, scriptToBind)
      }

      return script
   }

   private static Script loadScriptFromVars(String filename) {
      GroovyScriptEngine script_engine = new GroovyScriptEngine('vars/')
      Class<Script> script_class = script_engine.loadScriptByName("${filename}.groovy")
      Script script = script_class.newInstance()
      return script
   }

   private static void attachMocks(Script script) {
      script.getBinding().with {
         setVariable("echo", { println it })
         setVariable("step", { String s, Closure cl ->
            println "inside step ${s}"
            cl.call(s)
         })
      }
   }
}
