pipeline{
    agent {
        docker {
            image 'openjdk:11.0.11-jre-slim'
        }
    }

    stages {
        stage('Build') {
            sh 'mvn clean package'
        }

        stage('Test') {
            steps {
               // 執行測試
               sh 'mvn test'
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
}