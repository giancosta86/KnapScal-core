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

package info.gianlucacosta.knapscal.knapsack.dynamic.optimized

import info.gianlucacosta.knapscal.knapsack.{Item, Problem}

class OptimizedDynamicProgrammingSolver() {
  def solve(problem: Problem): Solution = {
    Solution(
      optimalSolution(problem.items)(problem.capacity)
    )
  }


  def optimalSolution(items: Seq[Item])(availableCapacity: Int): Int = {
    require(availableCapacity >= 0)

    if (items.isEmpty) {
      return 0
    }


    val currentItem = items.head

    return if (availableCapacity < currentItem.weight)
      optimalSolution(items.tail)(availableCapacity)
    else math.max(
      optimalSolution(items.tail)(availableCapacity),
      currentItem.profit + optimalSolution(items.tail)(availableCapacity - currentItem.weight)
    )
  }
}
