pipeline {

  agent any

  stages {

    stage('Checkout sources') {
      steps {
        deleteDir()
        checkout scm
      }
    }

    stage('Develop build') {
      steps {
          echo("Build success")
      }
    }
  }

  post {
    always {
        cleanWs()
    }
  }
}
