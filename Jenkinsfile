pipeline {
    agent any

    options {
        // This is required if you want to clean before build
        skipDefaultCheckout(true)
    }


    stages {
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
                // 部署應用程式，這裡假設使用 Docker 部署到容器中
                sh 'docker build -t my-anime .'
                sh 'docker run -d -p 8091:8080 my-anime'
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