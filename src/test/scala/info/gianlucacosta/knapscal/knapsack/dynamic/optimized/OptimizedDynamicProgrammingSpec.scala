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

package info.gianlucacosta.knapscal.knapsack.dynamic.optimized

import info.gianlucacosta.knapscal.knapsack.TestProblems
import org.scalatest.{FlatSpec, Matchers}

class OptimizedDynamicProgrammingSpec extends FlatSpec with Matchers {
  "Problem III.8" should "be solved" in {
    val problem = TestProblems.problem_III_8

    val solver = new OptimizedDynamicProgrammingSolver()
    val solution = solver.solve(problem)

    solution.value should be(40)
  }


  "Problem III.9" should "be solved" in {
    val problem = TestProblems.problem_III_9

    val solver = new OptimizedDynamicProgrammingSolver()
    val solution = solver.solve(problem)

    solution.value should be(79)
  }
}
