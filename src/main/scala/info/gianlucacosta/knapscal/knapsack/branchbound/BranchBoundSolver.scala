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

import info.gianlucacosta.knapscal.knapsack._

import scala.collection.mutable.ListBuffer


class BranchBoundSolver(upperBoundFunction: UpperBoundFunction) {
  private var problem: Problem = _

  private var bestNode: Node = _
  private var bestSolutionValue: Int = _

  private val nodes = new NodeBuffer()

  private val takenItems = ListBuffer[Item]()

  def solve(problem: Problem) = {
    this.problem = problem

    bestNode = null
    bestSolutionValue = 0

    nodes.clear()

    takenItems.clear()

    val rootNode = new RootNode()
    rootNode.upperBound = upperBoundFunction(problem, rootNode)
    nodes += rootNode

    exploreNode(rootNode, 0)

    require(bestNode != null, "No solution was found!")

    new Solution(rootNode, bestNode, nodes.toList)
  }


  private def exploreNode(currentNode: Node, currentLevel: Int): Unit = {
    val isSolution = currentLevel == problem.items.size

    if (isSolution) {
      currentNode.isSolution = true

      if (currentNode.totalProfit > bestSolutionValue) {
        bestSolutionValue = currentNode.totalProfit
        bestNode = currentNode
      }
    } else {
      if (currentNode.upperBound > bestSolutionValue) {
        val nextItem = problem.items(currentLevel)
        val nextLevel = currentLevel + 1

        if (currentNode.totalWeight + nextItem.weight <= problem.capacity) {
          takenItems += nextItem

          val takingNode = new TakingNode(nodes.size, nextLevel, takenItems.toList)
          nodes += takingNode

          if (nextLevel < problem.items.size) {
            takingNode.upperBound = currentNode.upperBound
          }
          currentNode.takingNode = takingNode


          exploreNode(takingNode, nextLevel)

          takenItems -= takenItems.last

          if (currentNode.upperBound <= bestSolutionValue) {
            currentNode.isStopped = true
            return
          }
        }


        val leavingNode = new LeavingNode(nodes.size, nextLevel, takenItems.toList)
        nodes += leavingNode

        if (nextLevel < problem.items.size) {
          leavingNode.upperBound = upperBoundFunction(problem, leavingNode)
        }
        currentNode.leavingNode = leavingNode


        exploreNode(leavingNode, nextLevel)
      } else {
        currentNode.isStopped = true
      }
    }
  }
}
