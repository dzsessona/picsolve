import scala.annotation.tailrec

/**
  * Created by diego on 05/05/16.
  */

trait Product {
  def getPrice: Long
}

case object Apple extends Product {
  override def getPrice = 60
}

case object Orange extends Product {
  override def getPrice = 25
}



object Cart extends App {

  @tailrec
  private def recGetTotal(items: Seq[Product], acc: Long): Option[Long] = items match {
    case Nil => Some(acc)
    case x :: xs => recGetTotal(xs, acc + x.getPrice)
  }

  private def getTotal(items: Seq[Product]): Option[Long] = {
    recGetTotal(items, 0)
    //Some(items.map(_.getPrice).sum)
  }

  def calculatePrice(items: Seq[Product]): Option[Long] = items match {
    case Nil => None
    case _ => getTotal(items)
  }

}
