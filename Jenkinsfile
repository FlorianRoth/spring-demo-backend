pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn install -DskipTests=true'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh 'mvn deploy'
            }
        }
        stage('Git Tag') {
            steps {
                echo 'Setting Git Tag....'
                sh 'printenv'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
