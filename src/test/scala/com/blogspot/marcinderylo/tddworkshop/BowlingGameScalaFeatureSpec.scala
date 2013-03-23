/*
 * BowlingGameScalaFeatureSpec.java
 * Date: 19.03.13
 * Time: 19:45
 *
 * @author Marcin Deryło, marcinderylo@gmail.com
 */
package com.blogspot.marcinderylo.tddworkshop

import org.scalatest.{GivenWhenThen, BeforeAndAfter, FeatureSpec}

class BowlingGameScalaFeatureSpec extends FeatureSpec with GivenWhenThen with BeforeAndAfter {
  var game: BowlingGame = _

  before {
    game = new BowlingGame
  }

  feature("Player scores without bonuses") {
    scenario("player scores all 0s") {
      When("player rolls 20 0s")
      rollMany(20, 0)

      Then("score should be 0")
      assert(game.score === 0)
    }
    scenario("player scores all 1s") {
      When("player rolls 20 1s")
      rollMany(20, 1)

      Then("score should be 20")
      assert(game.score === 20)
    }
  }
  feature("Player gets spare bonuses") {
    scenario("player gets a spare in first frame") {
      Given("player rolls a spare in first frame")
      rollSpare

      When("he gets positive score in next roll")
      game roll 5
      rollMany(17, 0)

      Then("that roll will be added as the spare bonus for first frame")
      assert(game.score === 10 + 5 + 5)
    }
  }

  feature("Player gets strike bonuses") {
    scenario("player gets a strike in first roll") {
      Given("player rolls a strike in first roll")
      rollStrike

      When("he rolls two positive scores in next two rolls")
      game roll 4
      game roll 3

      rollMany(16, 0)
      Then("those two rolls will be added as the strike bonus for first frame")

      assert(game.score === 10 + 7 + 4 + 3)
    }
    scenario("player rolls all strikes") {
      When("player rolls 10 strikes and another two in 10th frame")
      rollMany(10 + 2, 10)

      Then("his score should be the maximum = 300")
      assert(game.score === 300)
    }

    scenario("moar tests") {
      pending
    }
  }


  def rollStrike {
    game roll 10
  }

  def rollMany(n: Int, pins: Int) {
    for {
      rollNo <- 1 to n
    } game roll pins
  }

  def rollSpare {
    game roll 4
    game roll 6
  }

}
