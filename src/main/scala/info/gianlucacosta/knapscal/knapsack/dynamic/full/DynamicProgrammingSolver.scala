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

package info.gianlucacosta.knapscal.knapsack.dynamic.full

import info.gianlucacosta.knapscal.knapsack.{Item, Problem}

import scala.collection.mutable.ListBuffer

class DynamicProgrammingSolver {
  def solve(problem: Problem): Solution = {
    val iterations = ListBuffer[Iteration](new EmptyIteration())

    problem.items.foreach(item => {
      iterations += new ItemIteration(iterations.last, item, problem.capacity)
    })


    var bestProfit = 0
    var bestItems: Seq[Item] = Seq()

    iterations.last.activeStates.foreach(state => {
      if (state.totalProfit > bestProfit) {
        bestProfit = state.totalProfit
        bestItems = state.items
      }
    })


    return new Solution(iterations, bestItems, bestProfit)
  }
}
