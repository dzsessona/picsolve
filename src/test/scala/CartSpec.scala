/**
  * Created by diego on 05/05/16.
  */
import org.scalatest._

class CartSpec extends FlatSpec with Matchers {

  val referenceCart = Seq(Apple, Apple, Orange, Apple)

  "The checkout" should "return a None if it is emtpy" in {
    Cart.calculatePrice(Nil) should be (None)
  }

  it should "return the price of 60 p for a cart with one apple" in {
    Cart.calculatePrice(Seq(Apple)) should be (Some(60))
  }

  it should "return the price of 25 p for a cart with one ornge" in {
    Cart.calculatePrice(Seq(Orange)) should be (Some(25))
  }

  it should "return the correct price for the reference cart" in {
    Cart.calculatePrice(referenceCart) should be (Some(205))
  }
}