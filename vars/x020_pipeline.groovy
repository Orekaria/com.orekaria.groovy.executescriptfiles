def call(Map config = [:]) {

   x021_ciPipeline({ base ->
      // def log = new Logger('x020_pipeline')
      callerConfig = config
      targetLabel = 'master'

      println base.toString()
   })

}
