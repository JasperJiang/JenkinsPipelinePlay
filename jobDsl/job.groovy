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

buildPipelineView("DSLTEST/pipeline") {
    filterBuildQueue()
    filterExecutors()
    title(component.name)
    displayedBuilds(10)
    selectedJob("DSLTEST/Job1")
    alwaysAllowManualTrigger()
    showPipelineParameters(true)
    showPipelineDefinitionHeader(false)
    showPipelineParametersInHeaders(true)
    refreshFrequency(5)
}