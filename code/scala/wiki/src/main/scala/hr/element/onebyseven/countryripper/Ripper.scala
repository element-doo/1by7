package hr.element.onebyseven
package countryripper

trait Ripper[T <: CountryStub] {
  def ripCountries(): Seq[T]
}

