/* groovylint-disable CompileStatic, DuplicateStringLiteral */
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'printenv'
                echo 'build...'
                sh 'mvn clean validate compile'
            }
        }

        stage('test') {
            steps {
                sh 'printenv'
                echo 'test...'
                sh 'mvn clean test'
            }
        }

        stage('package') {
            steps {
                sh 'printenv'
                echo 'package...'
                sh 'mvn clean package'
            }
        }

        stage('cleanup') {
            steps {
                echo 'cleanup...'
                sh 'mvn clean'
            }
        }
    }
}
