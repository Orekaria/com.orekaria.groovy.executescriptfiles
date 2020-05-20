package src.case4

class PureScriptedDeclarative {

   Closure scripted = {
      node('master') {
         stage('do something') {
            echo 'Hello scripted!'
         }
      }
   }

   Closure declarative = {
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

   static void main(String[] args) {
      new PureScriptedDeclarative()
   }

   PureScriptedDeclarative() {
      scripted.delegate = new Steps()
      scripted()
      declarative.delegate = new Steps()
      declarative()
   }
}
