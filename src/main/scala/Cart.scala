/**
  * Created by diego on 05/05/16.
  */

trait Product {

}

case object Apple extends Product
case object Orange extends Product

object Cart extends App {

  def calculatePrice(items: List[Product]): Option[Long] = items match {
    case Nil => None
    case x :: xs => Some(2)
  }

}
