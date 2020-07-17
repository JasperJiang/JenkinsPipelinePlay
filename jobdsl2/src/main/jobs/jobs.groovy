package src.main.jobs

import com.demo.jobdsl.main.ComponentParser
import com.demo.jobdsl.main.domain.Repository
import com.demo.jobdsl.main.folders.UtilsFolder
import javaposse.jobdsl.dsl.DslFactory

DslFactory dslFactory = this

List<Repository> reposMetadata = new ComponentParser().parse(dslFactory)

reposMetadata.each {repo ->
    new UtilsFolder().build(delegate)
}
