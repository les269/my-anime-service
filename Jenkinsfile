pipeline {
    agent {
        docker {
            image 'openjdk:11'
        }
    }

    options {
        // This is required if you want to clean before build
        skipDefaultCheckout(true)
    }

    tools {
            maven 'Maven'
        }

    stages {
        stage('Build') {
            steps{
                withMaven  {
                    sh  'mvn clean compile'
                }
            }

        }

        stage('Test') {
            steps {
                withMaven {
                   // 執行測試
                   sh "mvn test"
                }
            }

        }
        stage('Deploy') {
            steps {
                // 部署應用程式，這裡假設使用 Docker 部署到容器中
                sh 'docker build -t myAnime .'
                sh 'docker run -d -p 8091:8080 myAnime'
            }
        }
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