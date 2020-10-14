package folder.subfoler
package subsubfoler

import a.b.c
import javax.inject.{Inject, Singleton}

class PerformanceDataServiceImpl()
  extends PerformanceDataService with Trait1 {

  def persistBulk(requestId: Long, dataSourceId: Long, bulk: IntegrationManagerDataBulk): Future[Unit] = {}

  private def ensureDataSourceExists(dataSourceId: Long): Future[Unit] = {
    def nestedMethod() = {

    }
  }
}