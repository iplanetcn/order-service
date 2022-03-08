pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'build...'
                sh "mvn clean test package spring-boot:repackage"
                sh "printenv"
                echo 'build complete'
            }
        }

        stage('test') {
            steps {
                echo 'test...'
            }
        }

        stage('package') {
            steps {
                echo 'package...'
            }
        }

        stage('deploy') {
            steps {
                echo 'deploy...'
            }
        }
    }
}