folder('DSLTEST') {
    displayName('DSLTEST DisplayName')
    description('Folder for DSLTEST')
}

job('DSLTEST/Job1') {
    scm {
        git('https://github.com/JasperJiang/JenkinsPlay')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}