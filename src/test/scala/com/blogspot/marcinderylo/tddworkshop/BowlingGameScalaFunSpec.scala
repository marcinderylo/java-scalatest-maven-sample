/*
 * BowlingGameScalaFunSpec.java
 * Date: 19.03.13
 * Time: 19:37
 *
 * @author Marcin Deryło, marcinderylo@gmail.com
 */
package com.blogspot.marcinderylo.tddworkshop

import org.scalatest.{BeforeAndAfter, FunSpec}

class BowlingGameScalaFunSpec extends FunSpec with BeforeAndAfter {

  var game: BowlingGame = _

  before {
    game = new BowlingGame
  }

  describe("A Bowling game") {
    it("should have score of 0 for all 0s") {
      rollMany(20, 0)
      assert(game.score === 0)
    }

    it("should have score of 20 for all 1s") {
      rollMany(20, 1)
      assert(game.score === 20)
    }

    it("should have add spare bonus") {
      rollSpare
      game roll 3
      rollMany(17, 0)
      assert(game.score === 10 + 3 + 3)
    }

    it("should have add strike bonus") {
      rollStrike
      game roll 5
      game roll 3
      rollMany(16, 0)
      assert(game.score === 10 + 5 + 3 + 5 + 3)
    }

    it("should have score of 300 for a perfect game of all strikes") {
      rollMany(10 + 2, 10)
      assert(game.score === 300)
    }

    it("moar tests") {
      pending
    }


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
