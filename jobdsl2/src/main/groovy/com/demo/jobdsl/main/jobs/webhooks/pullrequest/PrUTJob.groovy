package com.demo.jobdsl.main.jobs.webhooks.pullrequest

import com.demo.jobdsl.main.domain.Repository

class PrUTJob {

    void build(context, Repository repo){
        context.job('utils/pr-hook-unit-tests-' + repo.name){

            concurrentBuild()

            if (repo.components.size == 1){
                label(repo.components[0].image)
            }

            scm{
                git{
                    branch('${sha1}')
                    remote{
                        github(repo.gitRepositoryOwner, repo.gitRepositorySchema, repo.gitRepositoryHost)
                        credentials(repo.gitCredentialsId)
                        refspec("+refs/pull/*:refs/remotes/origin/pr/*")
                    }
                    extensions {
                        cloneOptions {
                            noTags(true)
                            shallow(true)
                            depth(100)
                        }
                    }
                }
            }

            logRotator {
                numToKeep(10)
                artifactNumToKeep(10)
            }

            if (repo.components.size == 1) {
                steps {
                    conditionalSteps {
                        stringsMatch('${sha1}', '${ghprbActualCommit}', false)
                    }
                    steps {
                        shell('echo "Check could not be run, please check for possible merge conflicts." > consoleTextTail.log')
                        shell('exit 1')
                    }
                    repo.components.each { component ->
                        // Run custom checks using make target ci-pr
                        shell("""#!/bin/bash
               make -C ${component.location} ci-pr COMPONENT_NAME=${component.name} &> /dev/stdout | tee consoleText.log; exitCode=\${PIPESTATUS[0]}; tail -50 consoleText.log > consoleTextTail.log; test \$exitCode -eq 0
               """)

                        shell("> consoleTextTail.log")
                    }
                }
            }else{
                steps{
                    conditionalSteps {
                        stringsMatch('${sha1}', '${ghprbActualCommit}', false)
                    }
                    steps {
                        shell('echo "Check could not be run, please check for possible merge conflicts." > consoleTextTail.log')
                        shell('exit 1')
                    }

                    repo.components.each { component ->
                        conditionalSteps {
                            condition {
                                not {
                                    shell("git diff --quiet --exit-code refs/remotes/origin/${repo.trunkBranch}..HEAD -- ${component.location}")
                                }
                            }
                            steps {
                                shell("git diff refs/remotes/origin/${repo.trunkBranch}..HEAD -- ${component.location}")
                                shell("echo detected changes for: " + component.location)

                                // Run custom checks using make target ci-pr
                                shell("""#!/bin/bash
                       make -C ${component.location} ci-pr COMPONENT_NAME=${component.name} &> /dev/stdout | tee consoleText.log; exitCode=\${PIPESTATUS[0]}; tail -50 consoleText.log > consoleTextTail.log; test \$exitCode -eq 0
                       """)

                                shell("> consoleTextTail.log")
                            }
                        }
                    }
                }
            }

        }
    }
}
