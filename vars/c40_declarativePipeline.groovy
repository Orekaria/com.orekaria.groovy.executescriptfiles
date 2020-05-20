def call() {
   pipeline {
      agent any
      stages {
         stage('do something') {
            steps {
               echo 'Hello declarative!'
            }
         }
      }
   }
}