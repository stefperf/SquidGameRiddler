package squidgameriddler


import scala.annotation.tailrec


case class Fraction(n: Long, d: Long) {
  import Fraction.{gcd, mcm}

  require(d != 0, "the denominator cannot be zero")

  def this(n: Long) = this(n, 1L)

  private val num: Long = n * Fraction.sign(d) / gcd(n, d)

  private val den: Long = d * Fraction.sign(d) / gcd(n, d)

  override def toString = s"$num/$den"

  def toDouble: Double = num.toDouble / den.toDouble

  def sign: Long = Fraction.sign(num)

  def +(that: Fraction): Fraction = {
    val newDen = mcm(this.den, that.den)
    val newNum = (for (f <- Seq(this, that)) yield newDen / f.den * f.num).sum
    Fraction(newNum, newDen)
  }

  def -(that: Fraction): Fraction = this + that * Fraction(-1L, 1L)

  def *(that: Fraction): Fraction = Fraction(this.num * that.num, this.den * that.den)

  def /(that: Fraction): Fraction = this * Fraction(that.den, that.num)
}


object Fraction {
  def sign(a: Long): Long = if (a > 0L) 1L else if (a < 0L) -1L else 0L

  @tailrec
  def gcd(a: Long, b: Long): Long = if (b == 0L) math.abs(a) else gcd(b, a % b)

  def mcm(a: Long, b: Long): Long = a * (b / gcd(a, b))
}


object SquidGameRiddler extends App {
  private val computedExpectations = collection.mutable.Map[(Int, Int), Fraction]()

  def calcExpectedSurvivors(nCompetitorsLeft: Int, nGlassSquaresLeft: Int): Fraction = {
    if (nCompetitorsLeft == 0) Fraction(0L, 1L)
    else if (nGlassSquaresLeft == 0) Fraction(nCompetitorsLeft.toLong, 1L)
    else if (computedExpectations.contains((nCompetitorsLeft, nGlassSquaresLeft)))
      computedExpectations((nCompetitorsLeft, nGlassSquaresLeft))
    else {
      var expectedSurvivors = Fraction(0L, 1L)
      var probabilitySituationOccurs = Fraction(1L, 1L)
      for (nGlassSquaresLeftAfterwards <- (nGlassSquaresLeft - 1) to 0 by -1) {
        // case where the 1st player guesses wrong at the s-th step
        probabilitySituationOccurs *= Fraction(1L, 2L)
        expectedSurvivors +=
          probabilitySituationOccurs * calcExpectedSurvivors(nCompetitorsLeft - 1, nGlassSquaresLeftAfterwards)
      }
      // case where the 1st player guesses all steps correctly
      expectedSurvivors += probabilitySituationOccurs * Fraction(nCompetitorsLeft.toLong, 1L)
      computedExpectations((nCompetitorsLeft, nGlassSquaresLeft)) = expectedSurvivors
      expectedSurvivors
    }
  }

  def analyzeGame(nCompetitorsLeft: Int, nGlassSquaresLeft: Int): Unit = {
    val expectedSurvivors = calcExpectedSurvivors(nCompetitorsLeft, nGlassSquaresLeft)
    var prevExpectation = Fraction(0L, 1L)
    for (playerNr <- 1 to nCompetitorsLeft) {
      val currExpectation = calcExpectedSurvivors(playerNr, nGlassSquaresLeft)
      val survivalProb = currExpectation - prevExpectation
      println(f"Player nr. $playerNr survives with probability $survivalProb ~= ${survivalProb.toDouble}%.10f.")
      prevExpectation = currExpectation
    }
    println(f"The total expected number of survivors is $expectedSurvivors ~= ${expectedSurvivors.toDouble}%.10f.")
  }

  val nrCompetitorsLeft = 16
  val nrGlassSquaresLeft = 18
  analyzeGame(nrCompetitorsLeft, nrGlassSquaresLeft)
}
