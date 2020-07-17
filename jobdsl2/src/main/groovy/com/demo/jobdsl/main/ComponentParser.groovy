package com.demo.jobdsl.main

import com.demo.jobdsl.main.domain.Component
import com.demo.jobdsl.main.domain.Repository
import javaposse.jobdsl.dsl.DslFactory
import org.yaml.snakeyaml.Yaml


class ComponentParser{

    List<Repository> parse(fileReader) {
        def deployTimeout = '240s'

        def repos = fileReader.readFileFromWorkspace("repos.out").split('\n')
        List<Repository> reposMetadata = new ArrayList<>()

        repos.each { repo ->
            Yaml parser = new Yaml()
            Map map = (Map) parser.load(fileReader.readFileFromWorkspace(repo))

            reposMetadata << new Repository(
                    path: repo.replace('ci-repo.yaml', ''),
                    name: map.get("name"),
                    trunkBranch: map.get("trunkBranch"),
                    gitCredentialsId : map.gitCredentialsId,
                    gitRepositoryOwner : map.gitRepositoryOwner,
                    gitRepositorySchema : map.gitRepositorySchema,
                    gitRepositoryHost : map.gitRepositoryHost,
                    dockerRegistry : map.dockerRegistry,
                    dockerRegistryCredentialsId : map.dockerRegistryCredentialsId,
                    components: new ArrayList<>()
            )
        }

        if (repos.size() == 0) {
            throw new IllegalStateException("No repositories found")
        }

        def components = fileReader.readFileFromWorkspace("components.out").split('\n')

        reposMetadata.each {repo ->
            components.each { component ->
                if (component.contains(repo.path)){
                    Yaml parser = new Yaml()
                    Map map = (Map) parser.load(fileReader.readFileFromWorkspace(component))

                    def componentPath = component.replace(repo.path, "").replace("ci.yaml", "")

                    if (componentPath == ''){
                        componentPath = "."
                    }

                    List<Map> componentList = []
                    if (map.containsKey("names")) {
                        map.names.each { name ->
                            componentList.add(new HashMap<>(map))
                            componentList.get(componentList.size() - 1).name = name
                        }
                    }else {
                        componentList.add(map)
                    }

                    componentList.each { c ->
                        repo.components << new Component(
                                image                   : c.image == null ? 'maven' : c.image,
                                imageName               : c.imageName == null ? c.name : c.imageName,
                                name                    : c.name,
                                location                : componentPath,
//                                type                    : ComponentType.valueOf((String) c.type.toUpperCase()),
                                serviceOverrides        : c.serviceOverrides,
//                                trigger                 : c.trigger == null ? BuildTrigger.CHANGE : BuildTrigger.valueOf((String) c.trigger.toUpperCase()),
                                triggerCron             : c.triggerCron,
                                triggerEnv              : c.triggerEnv,
                                quietPeriod             : c.quietPeriod,
                                deployTimeout           : c.deployTimeout == null ? deployTimeout : c.deployTimeout,
                                deploySkipVerification  : c.deploySkipVerification == null ? Boolean.FALSE : c.deploySkipVerification,
                                displayName             : c.displayName == null ? 'build' : c.displayName,
                                extensions              : c.extensions,
//                                testFramework           : c.testFramework == null ? TestFramework.JUNIT : TestFramework.valueOf((String) c.testFramework.toUpperCase())
                        )
                    }
                }
            }
        }
    }

}