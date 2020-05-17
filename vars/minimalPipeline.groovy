def call(Map callerConfig = [:]) {
   println 'inside'
   config = [:]
   ciPipeline(config)
   assert config.some_key == 'some_value'
   echo config.some_key
}
