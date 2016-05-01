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

import info.gianlucacosta.knapscal.knapsack.Problem

object UpperBoundFunctions {
  def basicDantzig(problem: Problem, node: Node): Int = {
    var totalProfit = node.totalProfit

    var spareCapacity = problem.capacity - node.totalWeight

    problem.items.drop(node.level).takeWhile(_ => spareCapacity > 0).foreach(item => {
      if (spareCapacity >= item.weight) {
        totalProfit += item.profit
        spareCapacity -= item.weight
      } else {
        totalProfit += math.floor(spareCapacity * item.relativeProfit.doubleValue()).toInt
        spareCapacity = 0
      }
    })

    return totalProfit
  }


  def optimizedDantzig(problem: Problem, node: Node): Int = {
    var totalProfit = node.totalProfit


    val initialSpareCapacity = problem.capacity - node.totalWeight
    var spareCapacity = initialSpareCapacity

    problem.items.drop(node.level).takeWhile(_ => spareCapacity > 0).foreach(item => {
      if (item.weight <= initialSpareCapacity) {
        if (spareCapacity >= item.weight) {
          totalProfit += item.profit
          spareCapacity -= item.weight
        } else {
          totalProfit += math.floor(spareCapacity * item.relativeProfit.doubleValue()).toInt
          spareCapacity = 0
        }
      }
    })

    return totalProfit
  }


  def martelloToth(problem: Problem, node: Node): Int = {
    var totalProfit = node.totalProfit

    var spareCapacity = problem.capacity - node.totalWeight

    problem.items.drop(node.level).takeWhile(_ => spareCapacity > 0).zipWithIndex.foreach { case (item, itemIndex) => {
      if (spareCapacity >= item.weight) {
        totalProfit += item.profit
        spareCapacity -= item.weight
      } else {
        val criticalItem = item
        val criticalItemIndex = itemIndex

        val nextItemBound = if (criticalItemIndex < problem.items.size - 1) {
          val nextItem = problem.items(criticalItemIndex + 1)
          math.floor(totalProfit + spareCapacity * nextItem.relativeProfit.doubleValue())
        } else {
          totalProfit
        }


        val criticalItemBound = if (criticalItemIndex > node.level) {
          val previousItem = problem.items(criticalItemIndex - 1)
          math.floor(totalProfit + criticalItem.profit - (criticalItem.weight - spareCapacity) * previousItem.relativeProfit.doubleValue())
        } else {
          totalProfit
        }

        totalProfit = math.max(
          nextItemBound,
          criticalItemBound
        ).toInt

        spareCapacity = 0
      }
    }
    }

    return totalProfit
  }
}
