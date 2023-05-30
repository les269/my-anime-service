pipeline {
    agent any

    options {
        // This is required if you want to clean before build
        skipDefaultCheckout(true)
    }


    stages {
        stage('Checkout') {
            steps {
                // 檢查源碼庫到 workspace
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/les269/my-anime-service.git']]])
            }
        }
        stage('init') {
            steps{
                sh  'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def rmContainer = sh script: 'docker rm my-anime-container', returnStatus: true
                    def rmImage = sh script: 'docker image rm my-anime', returnStatus: true
                    sh 'docker build -t my-anime .'
                    sh 'docker run -d -p 8091:8080 --name my-anime-container my-anime'
                }
            }
        }

//         stage('Clean Workspace') {
//             steps {
//                 cleanWs()
//             }
//         }
    }

//     post {
//             // Clean after build
//             always {
//                 cleanWs(cleanWhenNotBuilt: false,
//                         deleteDirs: true,
//                         disableDeferredWipeout: true,
//                         notFailBuild: true,
//                         patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
//                                    [pattern: '.propsfile', type: 'EXCLUDE']])
//             }
//         }
}