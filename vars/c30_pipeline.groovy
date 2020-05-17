import groovy.transform.Field

@Field
Map config = [:] // required for testing

def call(Map config = [:]) {
   this.config = config

   config.targetLabel = 'master'

   c31_ciPipeline({ base ->
      config.actionLevel = 4
      println base.toString()
   })

}
