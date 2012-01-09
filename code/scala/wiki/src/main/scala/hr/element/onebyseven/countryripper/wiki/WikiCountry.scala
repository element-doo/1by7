package hr.element.onebyseven
package countryripper
package wiki

import hr.element.onebyseven.common._

class WikiCountry(
    name: String,
    val a2: Alpha2,
    val a3: Alpha3,
    val n3: Numeric3) extends CountryStub(name) {

  override val toString =
    "%s:[%s,%s,%s]"format(name, a2, a3, n3)
}
