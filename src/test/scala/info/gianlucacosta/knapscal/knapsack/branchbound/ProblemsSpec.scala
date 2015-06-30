/*§
  ===========================================================================
  KnapScal - Core
  ===========================================================================
  Copyright (C) 2015 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.knapscal.knapsack.branchbound

import info.gianlucacosta.knapscal.knapsack.{Item, Problem, TestProblems}
import org.scalatest.{FlatSpec, Matchers}

class ProblemsSpec extends FlatSpec with Matchers {
  "A simple problem with one item" should "be solved by the item" in {
    val problem = new Problem(
      Seq(
        Item(13, 5)
      ),
      20
    )

    val solver = new BranchBoundSolver(UpperBoundFunctions.basicDantzig)
    val solution = solver.solve(problem)

    solution.bestNode.totalProfit should be(13)
  }


  "Problem III.8" should "be solved" in {
    val problem = TestProblems.problem_III_8
    val i = problem.oneBasedItems

    val solver = new BranchBoundSolver(UpperBoundFunctions.optimizedDantzig)
    val solution = solver.solve(problem)

    var nodes = solution.nodes
    nodes.size should be(17)

    solution.nodes(0) should be(
      new RootNode() {
        upperBound = 44
        takingNode = nodes(1).asInstanceOf[TakingNode]
        leavingNode = nodes(9).asInstanceOf[LeavingNode]
      }
    )

    solution.nodes(1) should be(
      new TakingNode(1, 1, Seq(i(1))) {
        upperBound = 44
        takingNode = nodes(2).asInstanceOf[TakingNode]
        leavingNode = nodes(8).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(2) should be(
      new TakingNode(2, 2, Seq(i(1), i(2))) {
        upperBound = 44
        takingNode = nodes(3).asInstanceOf[TakingNode]
        leavingNode = nodes(7).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(3) should be(
      new TakingNode(3, 3, Seq(i(1), i(2), i(3))) {
        upperBound = 44
        leavingNode = nodes(4).asInstanceOf[LeavingNode]
      }
    )

    solution.nodes(4) should be(
      new LeavingNode(4, 4, Seq(i(1), i(2), i(3))) {
        upperBound = 39
        leavingNode = nodes(5).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(5) should be(
      new LeavingNode(5, 5, Seq(i(1), i(2), i(3))) {
        upperBound = 39
        takingNode = nodes(6).asInstanceOf[TakingNode]
        isStopped = true
      }
    )


    solution.nodes(6) should be(
      new TakingNode(6, 6, Seq(i(1), i(2), i(3), i(6))) {
        isSolution = true
      }
    )

    solution.nodes(7) should be(
      new LeavingNode(7, 3, Seq(i(1), i(2))) {
        upperBound = 34
        isStopped = true
      }
    )


    solution.nodes(8) should be(
      new LeavingNode(8, 2, Seq(i(1))) {
        upperBound = 39
        isStopped = true
      }
    )


    solution.nodes(9) should be(
      new LeavingNode(9, 1, Seq()) {
        upperBound = 43
        takingNode = nodes(10).asInstanceOf[TakingNode]
        leavingNode = nodes(16).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(10) should be(
      new TakingNode(10, 2, Seq(i(2))) {
        upperBound = 43
        takingNode = nodes(11).asInstanceOf[TakingNode]
        leavingNode = nodes(15).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(11) should be(
      new TakingNode(11, 3, Seq(i(2), i(3))) {
        upperBound = 43
        leavingNode = nodes(12).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(12) should be(
      new LeavingNode(12, 4, Seq(i(2), i(3))) {
        upperBound = 40
        isStopped = true
        takingNode = nodes(13).asInstanceOf[TakingNode]
      }
    )


    solution.nodes(13) should be(
      new TakingNode(13, 5, Seq(i(2), i(3), i(5))) {
        upperBound = 40
        leavingNode = nodes(14).asInstanceOf[LeavingNode]
      }
    )


    solution.nodes(14) should be(
      new LeavingNode(14, 6, Seq(i(2), i(3), i(5))) {
        isSolution = true
      }
    )


    solution.nodes(15) should be(
      new LeavingNode(15, 3, Seq(i(2))) {
        upperBound = 31
        isStopped = true
      }
    )


    solution.nodes(16) should be(
      new LeavingNode(16, 2, Seq()) {
        upperBound = 39
        isStopped = true
      }
    )


    solution.bestNode should be(nodes(14))
  }

  "Problem III.9" should "be solved" in {
    val problem = TestProblems.problem_III_9
    val i = problem.oneBasedItems

    val solver = new BranchBoundSolver(UpperBoundFunctions.basicDantzig)
    val solution = solver.solve(problem)

    var nodes = solution.nodes
    nodes.size should be(10)


    nodes(0) should be(
      new RootNode() {
        upperBound = 85
        takingNode = nodes(1).asInstanceOf[TakingNode]
        leavingNode = nodes(9).asInstanceOf[LeavingNode]
      }
    )


    nodes(1) should be(
      new TakingNode(1, 1, Seq(i(1))) {
        upperBound = 85
        leavingNode = nodes(2).asInstanceOf[LeavingNode]
      }
    )


    nodes(2) should be(
      new LeavingNode(2, 2, Seq(i(1))) {
        upperBound = 84
        takingNode = nodes(3).asInstanceOf[TakingNode]
        leavingNode = nodes(6).asInstanceOf[LeavingNode]
      }
    )


    nodes(3) should be(
      new TakingNode(3, 3, Seq(i(1), i(3))) {
        upperBound = 84
        leavingNode = nodes(4).asInstanceOf[LeavingNode]
      }
    )


    nodes(4) should be(
      new LeavingNode(4, 4, Seq(i(1), i(3))) {
        upperBound = 50
        isStopped = true
        takingNode = nodes(5).asInstanceOf[TakingNode]
      }
    )


    nodes(5) should be(
      new TakingNode(5, 5, Seq(i(1), i(3), i(5))) {
        isSolution = true
      }
    )


    nodes(6) should be(
      new LeavingNode(6, 3, Seq(i(1))) {
        upperBound = 79
        isStopped = true
        takingNode = nodes(7).asInstanceOf[TakingNode]
      }
    )


    nodes(7) should be(
      new TakingNode(7, 4, Seq(i(1), i(4))) {
        upperBound = 79
        isStopped = true
        takingNode = nodes(8).asInstanceOf[TakingNode]
      }
    )


    nodes(8) should be(
      new TakingNode(8, 5, Seq(i(1), i(4), i(5))) {
        isSolution = true
      }
    )


    nodes(9) should be(
      new LeavingNode(9, 1, Seq()) {
        upperBound = 61
        isStopped = true
      }
    )

    solution.bestNode should be(nodes(8))
  }
}
