/**
  * Created by diego on 05/05/16.
  */
import org.scalatest._

class CartSpec extends FlatSpec with Matchers {

  "The checkout" should "return a None if it is emtpy" in {
    Cart.calculatePrice(Nil) should be (None)
  }
}