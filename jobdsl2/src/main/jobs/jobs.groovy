import com.demo.jobdsl.main.ComponentParser
import com.demo.jobdsl.main.domain.Repository
import com.demo.jobdsl.main.folders.UtilsFolder
import com.demo.jobdsl.main.jobs.webhooks.pullrequest.PrUTJob
import javaposse.jobdsl.dsl.DslFactory

DslFactory dslFactory = this

List<Repository> reposMetadata = new ComponentParser().parse(dslFactory)

reposMetadata.each {repo ->
    new UtilsFolder().build(delegate)
    new PrUTJob().build(delegate, repo)
}
