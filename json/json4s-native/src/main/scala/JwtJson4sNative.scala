package pdi.jwt

import org.json4s._
import org.json4s.JsonDSL.WithBigDecimal._
import org.json4s.native.JsonMethods._
import org.json4s.native.JsonMethods.{parse => jparse}
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}

object JwtJson4s extends JwtJson4sCommon {
  protected def parse(value: String): JObject = jparse(value) match {
    case res: JObject => res
    case _ => throw new RuntimeException(s"Couldn't parse [$value] to a JObject")
  }

  protected def stringify(value: JObject): String = compact(render(value))

  implicit val formats = Serialization.formats(NoTypeHints)

  protected def parseHeader(header: String): JwtHeader = readHeader(parse(header))
  protected def parseClaim(claim: String): JwtClaim = readClaim(parse(claim))
}
