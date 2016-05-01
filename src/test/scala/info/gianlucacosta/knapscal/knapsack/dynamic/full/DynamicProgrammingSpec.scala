/*§
  ===========================================================================
  KnapScal - Core
  ===========================================================================
  Copyright (C) 2015-2016 Gianluca Costa
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

package info.gianlucacosta.knapscal.knapsack.dynamic.full

import info.gianlucacosta.knapscal.knapsack.TestProblems
import org.scalatest.{FlatSpec, Matchers}

class DynamicProgrammingSpec extends FlatSpec with Matchers {
  "Problem III.8" should "be solved" in {
    val problem = TestProblems.problem_III_8
    val i = problem.oneBasedItems

    val solver = new DynamicProgrammingSolver()
    val solution = solver.solve(problem)

    solution.value should be(40)
    solution.items should be(
      Seq(i(2), i(3), i(5))
    )
  }


  "Problem III.9" should "be solved" in {
    val solver = new DynamicProgrammingSolver()

    val problem = TestProblems.problem_III_9
    val i = problem.oneBasedItems

    val solution = solver.solve(problem)

    solution.value should be(79)

    solution.items should be(
      Seq(i(1), i(4), i(5))
    )

    //Iteration 0
    solution.iterations(0).activeStates should be(
      Seq(
        State(Seq())
      )
    )
    solution.iterations(0).dominatedStates should be(empty)


    //Iteration 1
    solution.iterations(1).activeStates should be(
      Seq(
        State(Seq()),
        State(Seq(i(1)))
      )
    )
    solution.iterations(1).dominatedStates should be(empty)


    //Iteration 2
    solution.iterations(2).activeStates should be(
      Seq(
        State(Seq()),
        State(Seq(i(1))),
        State(Seq(i(2)))
      )
    )
    solution.iterations(2).dominatedStates should be(empty)


    //Iteration 3
    solution.iterations(3).activeStates should be(
      Seq(
        State(Seq()),
        State(Seq(i(1))),
        State(Seq(i(2))),
        State(Seq(i(3))),
        State(Seq(i(1), i(3))),
        State(Seq(i(2), i(3)))
      )
    )
    solution.iterations(3).dominatedStates should be(empty)


    //Iteration 4
    solution.iterations(4).activeStates should be(
      Seq(
        State(Seq()),
        State(Seq(i(1))),
        State(Seq(i(2))),
        State(Seq(i(3))),
        State(Seq(i(1), i(3))),
        State(Seq(i(1), i(4)))
      )
    )
    solution.iterations(4).dominatedStates should be(
      Set(
        State(Seq(i(2), i(3))),
        State(Seq(i(4))),
        State(Seq(i(3), i(4)))
      )
    )


    //Iteration 5
    solution.iterations(5).activeStates should be(
      Seq(
        State(Seq()),
        State(Seq(i(1))),
        State(Seq(i(2))),
        State(Seq(i(3))),
        State(Seq(i(1), i(3))),
        State(Seq(i(1), i(4))),
        State(Seq(i(5))),
        State(Seq(i(1), i(5))),
        State(Seq(i(3), i(5))),
        State(Seq(i(1), i(3), i(5))),
        State(Seq(i(1), i(4), i(5)))
      )
    )
    solution.iterations(5).dominatedStates should be(
      Set(
        State(Seq(i(2), i(5)))
      )
    )
  }
}