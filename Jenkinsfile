pipeline{
    agent none
        stages{
            stage('checkout'){
                agent any
                steps{
                    git 'https://github.com/Martin1196155/Edureka_Final_Project.git'
                }
            }
            
            stage('Docker_Build'){
                agent any
                steps{
                    sh 'sudo docker build -t martin1051/myappbypipe:latest .'
                }
            }
            
            stage('Docker_Push'){
                agent any
                steps{
                    withCredentials([string(credentialsId: 'Password', variable: 'Password')]) {
                        sh 'sudo docker login -u martin1051 -p $Password'
                    }
                
                    sh 'sudo docker push martin1051/myappbypipe:latest'
                }
            }
            stage('Docker_CleanUP'){
                agent any
                steps{
                    sh 'sudo docker ps -f name=mycont -q | xargs --no-run-if-empty sudo docker container stop'
                    sh 'sudo docker container ls -a -fname=mycont -q | xargs -r sudo docker container rm'
                }
            }
            stage('Docker_Run'){
                agent any
                steps{
                    sh 'sudo docker run --name mycont -d -p 8140:80 martin1051/myappbypipe:latest'
                }
            }
            
            stage('Selenium_Test'){
                agent {label'Win_Test_server'}
                steps{
                    git 'https://github.com/Martin1196155/Edureka_Final_Project.git'
                    bat 'mvn test'
                }
            }            
            
        }   
}
