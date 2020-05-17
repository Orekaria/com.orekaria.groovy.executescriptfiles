def call(Map callerConfig = [:]) {
   isSuccess = false
   callerConfig.isSuccess = false
   callerConfig.some_key = 'some_value'
   println 'inside someother ' + callerConfig.some_key
   echo 'uno'
   (1..3).each {
      echo it
   }
   isSuccess = true
   callerConfig.isSuccess = true
   c12_yetAnotherLevel()
}
