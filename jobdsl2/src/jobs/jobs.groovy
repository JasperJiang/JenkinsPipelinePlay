import com.demo.jobdsl.main.ComponentParser
import com.demo.jobdsl.main.domain.Repository
import javaposse.jobdsl.dsl.DslFactory

DslFactory dslFactory = this

List<Repository> reposMetadata = new ComponentParser().parse(dslFactory)
