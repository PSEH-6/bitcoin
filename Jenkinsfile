pipeline {
    agent any

    stages {
        stage('BuildAndTest') {
            steps {
                echo 'Building and Running JunitTests..'
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            when  {
                expression {currentBuild.result == null || currentBuild.result == 'SUCCESS' }
                  }
            steps {
                echo 'Deploying....'

                sh 'pwd'
                sh 'scp -i "/home/bitnami/jenkins.pem" target/bitcoin-0.1.0.jar ubuntu@18.222.87.31:bitcoin-0.1.0.jar'
                sh 'ssh -i "/home/bitnami/jenkins.pem" ubuntu@18.222.87.31 "java -jar bitcoin-0.1.0.jar"'
            }
        }
    }
}
