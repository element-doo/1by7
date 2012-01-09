package hr.element.onebyseven
package countryripper
package iso

import common._

class ISOCountry(
    name: String,
    val a2: Alpha2) extends CountryStub(name) {
  require(name.toUpperCase == name, "ISOCountry name cannot contain lowercaes letters!")

  override val toString =
    "%s:[%s]" format(name, a2)
}
