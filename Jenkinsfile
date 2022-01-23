pipeline {
  agent {
    docker {
      args '-v /root/.m2:/root/.m2'
      image 'maven:3.8.4-eclipse-temurin-16'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('Publish') {
      steps {
        script {
          pom = readMavenPom file: "pom.xml";
        filesByGlob = findFiles(glob: "target/*.${pom.packaging}");

        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"

        artifactPath = filesByGlob[0].path;

        nexusArtifactUploader(
          nexusVersion: "nexus3",
          protocol: "https",
          nexusUrl: "nexus.premsiserv.com",
          groupId: pom.groupId,
          version: pom.version,
          repository: "maven-snapshots",
          credentialsId:"nexusjenkinsuser",
          artifacts: [
            [
              artifactId: pom.artifactId,
              classifier: '',
              file: artifactPath,
              type: pom.packaging
            ],
            [
              artifactId: pom.artifactId,
              classifier: '',
              file: "pom.xml",
              type: "pom"
            ]
          ]
        )
        }
      }
    }

  }
}