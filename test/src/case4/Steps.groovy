package src.case4

class Steps {
   def any = null

   def echo = { String s ->
      println s
   }

   def pipeline = { Closure cl ->
      cl('called me')
   }

   def agent = { Closure cl ->
      if (cl != null)
         cl('called me')
   }

   def node = { String s, Closure cl ->
      cl('called me')
   }

   def stages = { Closure cl ->
      cl()
   }

   def stage = { String s, Closure cl ->
      cl(s)
   }

   def steps = { Closure cl ->
      cl()
   }

   def propertyMissing(String property) {
      println("[Missing] ${property}")
   }
}