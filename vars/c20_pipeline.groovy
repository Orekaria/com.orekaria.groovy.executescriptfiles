println "[execute c20_pipeline]"
levelClassVar = true // not transferred to children

def call(Map inConfig = [:]) {

   def levelClassMethodNotTransferredVar = true // def -> not transferred
   levelClassMethodTransferredVar = true // no def -> transferred
   this.levelClassMethodVar = true // this. -> transferred
   inConfig.levelClassMethodConfigVar = true // transferred

   step('c20_pipeline.call') {
   }

   c21_ciPipeline({ base ->
      println "[    start of c20_pipeline (when c21_ciPipeline executes it)]"

      config = inConfig

      // echo = { Object s -> // methods can be intercepted
      //    println('intercepted!')
      // }

      step('c20_pipeline') {
      }

      targetLabel = 'master'
      def levelClassMethodClosureNotTransferredVar = true // def -> not transferred (private)
      levelClassMethodClosureVar = true // transferred
      config.levelClassMethodClosureInConfigVar = true // in config -> transferred

      println "[    end of c20_pipeline]"
   })

}
