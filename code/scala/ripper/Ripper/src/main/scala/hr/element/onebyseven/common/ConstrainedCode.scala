package hr.element.onebyseven
package common

import scala.collection.immutable.NumericRange

abstract class ConstrainedCode(code: String, length: Int, chars: NumericRange[Char]) extends Product {
  if (code eq null) throw new NullPointerException(productPrefix + " code was null!")
  if (code.length != length) throw new IllegalArgumentException(productPrefix + " is not " + length + " characters: " + code)
  if (!code.forall(chars.contains)) throw new IllegalArgumentException(productPrefix +
    " cannot contain characters other than " + chars.start + '-' + chars.end + ": " + code)

  override val toString = code
}

abstract class AlphaCode(code: String, length: Int) extends ConstrainedCode(code, length, 'A' to 'Z')
abstract class NumericCode(code: String, length: Int) extends ConstrainedCode(code, length, '0' to '9')

case class Alpha2(code: String) extends AlphaCode(code, 2)
case class Alpha3(code: String) extends AlphaCode(code, 3)
case class Numeric3(code: String) extends NumericCode(code, 3)
