pipeline {

  agent any

  environment {
    TERM = "xterm-256color"
    LANG = "en_US.UTF-8"
    LANGUAGE = "en_US.UTF-8"
    LC_ALL = "en_US.UTF-8"
    FASTLANE_DONT_STORE_PASS = "1"
  }

  options {
    ansiColor('xterm')
    buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    disableConcurrentBuilds()
  }

  parameters {
    string(name: 'BUILD_VERSION', defaultValue: '1', description: 'The version code CFBundleVersion (build version e.g. 123) to use for the application')
    booleanParam(name: 'ESSENTIAL_BULDS_ONLY', defaultValue: true, description: 'Should only the builds for DV and ST be created')
  }

  stages {

    stage('Checkout sources') {
      steps {
        deleteDir()
        checkout scm
      }
    }

//    stage('Setup fastlane') {
//      steps {
//        sh """#!/bin/bash
//          bundle install
//        """
//      }
//    }

    stage('Develop build') {
      steps {
          echo("Build success")
//        lock("ios-toolchain") {
//          withCredentials([string(credentialsId: 'bee86baf-673b-4ded-b25d-abbcf157e9b7', variable: 'MATCH_PASSWORD'),
//                           string(credentialsId: 'a61d9aec-5b1b-4698-b840-94adc098c5af', variable: 'MATCH_KEYCHAIN_PASSWORD')
//                           ]) {
//            sh """#!/bin/bash
//              bundle exec fastlane ios pr output_suffix:${BRANCH_NAME} build_number:${params.BUILD_VERSION}
//            """
//          }
//        }
      }
      when {
        environment name: 'ESSENTIAL_BULDS_ONLY', value: 'false'
      }
    }

    stage('Archive artifacts') {
      steps {
        archiveArtifacts artifacts: '**/*.ipa,**/*.dSYM.zip,**/*simulator.zip'
      }
    }
  }

  post {
    always {
        cleanWs()
    }
  }
}
