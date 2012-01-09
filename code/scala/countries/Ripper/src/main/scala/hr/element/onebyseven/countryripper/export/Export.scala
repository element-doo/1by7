package hr.element.onebyseven
package countryripper
package export

import hr.ngs.templater.Configuration

abstract class Export {
  def getDF() = {
    Configuration.factory()
  }
}