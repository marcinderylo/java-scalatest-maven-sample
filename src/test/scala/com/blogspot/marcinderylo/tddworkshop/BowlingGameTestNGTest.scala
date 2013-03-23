/*
 * BowlingGameTest.java
 * Date: 19.03.13
 * Time: 19:14
 *
 * @author Marcin Deryło, marcinderylo@gmail.com
 */
package com.blogspot.marcinderylo.tddworkshop

import org.testng.annotations.{BeforeMethod, Test}
import org.scalatest.Assertions

class BowlingGameTestNGTest extends Assertions {

  var game: BowlingGame = _ // arrgh! damn you TestNG! Y U reuse test class instances?!

  @BeforeMethod
  def initialize() {
    game = new BowlingGame()
  }

  @Test
  def gutterGame {
    rollMany(20, 0)
    assert(game.score === 0)
  }

  @Test
  def allOnes {
    rollMany(20, 1)
    assert(game.score === 20)
  }

  @Test
  def oneSpare {
    rollSpare
    game roll 3
    rollMany(17, 0)
    assert(game.score === 10 + 3 + 3)
  }

  @Test
  def oneStrike {
    rollStrike
    game roll 5
    game roll 3
    rollMany(16, 0)
    assert(game.score === 10 + 5 + 3 + 5 + 3)
  }

  @Test
  def perfectGame {
    rollMany(10 + 2, 10)
    assert(game.score === 300)
  }

  def rollMany(n: Int, pins: Int) {
    for {i <- 1 to n} {
      game roll pins
    }
  }

  def rollStrike {
    game roll 10
  }

  def rollSpare {
    game roll 5
    game roll 5
  }
}
