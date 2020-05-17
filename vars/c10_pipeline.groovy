def call(Map callerConfig = [:]) {
   println 'inside'
   config = [:]
   c11_ciPipeline(config)
   assert config.some_key == 'some_value'
   echo config.some_key
}
