package src.case4

class MyDSL {
   void foo(int x, int y, Closure cl) {
      println('aaa')
      cl()
   }

   void setBar(String a) {
      println('bbb')
   }

}

def script = src.GroovyScriptHelper.loadDelegatingScript("my.dsl")
script.setDelegate(new MyDSL())
script.run()
