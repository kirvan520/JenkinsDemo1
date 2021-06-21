pipeline {
  agent any
  parameters {
    booleanParam(name: 'psc_DV',
      defaultValue: true)
    booleanParam(name: 'psc_ST')
    booleanParam(name: 'psc_MT',
      defaultValue: false)
    booleanParam(name: 'psc_IT',
      defaultValue: false)
  }

  stages {
    stage('Checkout sources') {
      steps {
        deleteDir()
        checkout scm
      }
    }
      
    stage ('Build psc_DV') {
      when {
        expression { params.psc_DV == true }
      }
      steps {
        echo "psc_DV build started.."
      }
    }

  stage ('Build psc_ST') {
      when {
        expression { params.psc_ST == true }
      }
      steps {
        echo "psc_ST build started.."
      }
  }

  stage ('Build psc_MT') {
      when {
        expression { params.psc_MT == true }
      }
      steps {
        echo "psc_MT build started.."
      }
  }

  stage ('Build psc_IT') {
    when {
      expression { params.psc_IT == true }
    }
    steps {
      echo "psc_IT build started.."
    }
  }

  }
  post {
    always {
        cleanWs()
    }
  }
}