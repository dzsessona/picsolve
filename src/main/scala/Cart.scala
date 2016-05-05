import scala.annotation.tailrec

/**
  * Created by diego on 05/05/16.
  */

//sealed would be nice, also could use java style enum using val to ovverride getPrice
trait Product {
  def getPrice: Long
}

case object Apple extends Product {
  override def getPrice = 60
}

case object Orange extends Product {
  override def getPrice = 25
}



trait Discounter {
  def buyOneGetOneFree(quantity: Int, prod: Product): Long = {
    val discPrice = quantity / 2 * prod.getPrice
    if(quantity % 2 == 0) discPrice else discPrice + prod.getPrice
  }

  def `3by2`(quantity: Int, prod: Product): Long = {
    val originalPrice = prod.getPrice * quantity
    val toDiscount = quantity / 3
    originalPrice - (toDiscount * prod.getPrice)
  }
}


// extends App so could accept command line parameters,
// i.e. using scallop ?
object Cart extends App with Discounter {

  @tailrec
  private def recGetTotal(items: Seq[Product], acc: Long): Option[Long] = items match {
    case Nil => Some(acc)
    case x :: xs => recGetTotal(xs, acc + x.getPrice)
  }

  private def getTotal(items: Seq[Product]): Option[Long] = {
    recGetTotal(items, 0)
    //following is an alternative to not use recursion
    //Some(items.map(_.getPrice).sum)
  }


  def calculatePrice(items: Seq[Product]): Option[Long] = items match {
    case Nil => None
    case _ => getTotal(items)
  }

  private def toGroupedRepresentation(items: Seq[Product]): Map[Product, Int] = {
    items.groupBy[Product](x => x).mapValues(_.length)
  }

  //instead of hardcoding here the discount type for each product could be a map of prod - option[discout]
  //so easy to change for example to get 3 by discount on apples instead of oranges.
  //tests need to be changed accordingly to be product agnostic for the discount type
  // probably look at some design pattern ?
  def applyDicount(items: Seq[Product]): Option[Long] = {

    def applyDiscounts: Map[Product, Long] = {
      val discounted: Map[Product, Long] = toGroupedRepresentation(items).map { case (p, q) =>
        p match {
          case Apple => Apple -> buyOneGetOneFree(q, Apple)
          case Orange => Orange -> `3by2`(q, Orange)
        }
      }
      discounted
    }
    Some(applyDiscounts.values.sum)
  }

}
