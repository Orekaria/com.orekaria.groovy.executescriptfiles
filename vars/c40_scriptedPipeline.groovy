def call() {
   node('master') {
      stage('do something') {
         echo 'Hello scripted!'
      }
   }
}