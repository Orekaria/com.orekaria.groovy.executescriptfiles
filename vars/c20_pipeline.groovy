def call(Map config = [:]) {

   c21_ciPipeline({ base ->
      // def log = new Logger('c20_pipeline')
      callerConfig = config
      targetLabel = 'master'

      println base.toString()
   })

}
