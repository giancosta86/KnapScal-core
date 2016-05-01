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

package info.gianlucacosta.knapscal.knapsack.branchbound

import info.gianlucacosta.knapscal.knapsack.{Item, Problem, TestProblems}
import org.scalatest.{FlatSpec, Matchers}

class UpperBoundsSpec extends FlatSpec with Matchers {
  "Optimized Dantzig bound" should "be correct for RootNode III.8" in {
    val problem = TestProblems.problem_III_8
    val rootNode = new RootNode()
    val upperBound = UpperBoundFunctions.optimizedDantzig(problem, rootNode)

    upperBound should be(44)
  }


  "Basic Dantzig bound" should "be correct for RootNode III.9" in {
    val problem = TestProblems.problem_III_9
    val rootNode = new RootNode()
    val upperBound = UpperBoundFunctions.basicDantzig(problem, rootNode)

    upperBound should be(85)
  }


  "Martello-Toth's bound" should "be correctly computed for Problem III.8" in {
    val problem = TestProblems.problem_III_8
    val rootNode = new RootNode()
    val upperBound = UpperBoundFunctions.martelloToth(problem, rootNode)

    upperBound should be(41)
  }


  "Martello-Toth's bound" should "be correctly computed when the critical item is the last item" in {
    val problem = new Problem(
      Seq(
        Item(10, 4),
        Item(3, 15)
      ),

      15
    )
    val rootNode = new RootNode()
    val upperBound = UpperBoundFunctions.martelloToth(problem, rootNode)

    upperBound should be(10)
  }
}
