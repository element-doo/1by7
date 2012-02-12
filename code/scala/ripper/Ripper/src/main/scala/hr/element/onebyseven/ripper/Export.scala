package hr.element.onebyseven
package ripper

import hr.ngs.templater.Configuration

abstract class Export {
  def getDF() = {
    Configuration.factory()
  }
}
