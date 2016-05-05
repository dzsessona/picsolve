/**
  * Created by diego on 05/05/16.
  */
import org.scalatest._

class CartDiscountedSpec extends FlatSpec with Matchers {

  val referenceCart = Seq(Apple, Apple, Orange, Apple)

  "The checkout with discounts" should "return 60 for one or two apples" in {
    Cart.applyDicount(List(Apple)) should be (Some(60))
    Cart.applyDicount(List(Apple, Apple)) should be (Some(60))
  }

  it should "return 120 for 3 or 4 apples" in {
    Cart.applyDicount(List(Apple, Apple, Apple)) should be (Some(120))
    Cart.applyDicount(List(Apple, Apple, Apple, Apple)) should be (Some(120))
  }

  it should "return 50 for 3 oranges" in {
    Cart.applyDicount(List(Orange, Orange, Orange)) should be (Some(50))
  }

  it should "return 75 for 4 oranges" in {
    Cart.applyDicount(List.fill[Product](4)(Orange)) should be (Some(75))
  }

  it should "return 145 for the reference discounted cart" in {
    Cart.applyDicount(referenceCart) should be (Some(145))
  }

}