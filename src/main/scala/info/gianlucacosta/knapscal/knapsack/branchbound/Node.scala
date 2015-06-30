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

import info.gianlucacosta.knapscal.knapsack.{Item, ItemsFormatter}


abstract class Node(val index: Int, val level: Int, val takenItems: Seq[Item]) {
  val totalProfit = takenItems.map(_.profit).sum
  val totalWeight = takenItems.map(_.weight).sum

  private var _upperBound = 0

  private var _takingNode: Option[TakingNode] = None
  private var _leavingNode: Option[LeavingNode] = None

  private var _isStopped = false
  private var _isSolution = false


  def upperBound = _upperBound

  private[branchbound] def upperBound_=(value: Int) {
    _upperBound = value
  }


  def takingNode = _takingNode

  private[branchbound] def takingNode_=(value: TakingNode) {
    _takingNode = Some(value)
  }


  def leavingNode = _leavingNode

  private[branchbound] def leavingNode_=(value: LeavingNode) {
    _leavingNode = Some(value)
  }


  def isStopped = _isStopped

  private[branchbound] def isStopped_=(value: Boolean): Unit = {
    require(!_isStopped)
    require(value)

    _isStopped = value
  }

  def isSolution = _isSolution

  private[branchbound] def isSolution_=(value: Boolean): Unit = {
    require(!_isSolution)
    require(value)

    _isSolution = value
  }


  def branchString: String

  def isUpperBoundComputed: Boolean


  override def equals(obj: scala.Any): Boolean = obj match {
    case other: Node => (index == other.index) &&
      (level == other.level) &&
      (takenItems == other.takenItems) &&
      (upperBound == other.upperBound) &&
      (takingNode == other.takingNode) &&
      (leavingNode == other.leavingNode) &&
      (isStopped == other.isStopped) &&
      (isSolution == other.isSolution) &&
      (branchString == other.branchString) &&
      (isUpperBoundComputed == other.isUpperBoundComputed)
  }

  override def hashCode(): Int = index.hashCode()

  override def toString() = {
    val result = new StringBuilder

    result.append(s"${index} (${branchString});")

    if (upperBound > 0) {
      result.append(s"   ${if (isUpperBoundComputed) "Ū" else "U"} = ${upperBound};")
    }

    result.append(s"   P = ${totalProfit};")
    result.append(s"    W = ${totalWeight};")
    result.append(s"    I = ${ItemsFormatter.format(takenItems)}")

    if (isStopped) {
      result.append(";   *STOP*")
    } else if (isSolution) {
      result.append(";   *SOL*")
    }

    result.toString()
  }
}
