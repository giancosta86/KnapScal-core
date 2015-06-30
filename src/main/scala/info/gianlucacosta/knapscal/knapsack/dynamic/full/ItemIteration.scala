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

import info.gianlucacosta.knapscal.knapsack.Item

import scala.collection.mutable


private class ItemIteration(previousIteration: Iteration, item: Item, capacity: Int) extends Iteration {
  override val (activeStates, dominatedStates) = {
    var activeStates = previousIteration.activeStates ++
      previousIteration.activeStates
        .map(state => state + item)
        .filter(state => state.totalWeight <= capacity)


    val dominatedStates = mutable.Set[State]()

    activeStates.foreach(possiblyDominating => {
      activeStates.foreach(possiblyDominated => {
        if (possiblyDominating.dominates(possiblyDominated)) {
          if (!dominatedStates.contains(possiblyDominating) && !dominatedStates.contains(possiblyDominated)) {
            dominatedStates.add(possiblyDominated)
          }
        }
      })
    })

    activeStates = activeStates.filter(state => !dominatedStates.contains(state))

    (activeStates, dominatedStates.toSet)
  }
}
