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

import info.gianlucacosta.knapscal.knapsack.{Item, ItemsFormatter}

case class State private[full](items: Seq[Item]) {
  val totalProfit = items.map(_.profit).sum
  val totalWeight = items.map(_.weight).sum

  def dominates(other: State) = (totalWeight <= other.totalWeight && totalProfit >= other.totalProfit) && (this != other)

  def +(item: Item) = new State(items :+ item)

  override def toString: String = s"(${ItemsFormatter.format(items)}, ${totalProfit}, ${totalWeight})"
}
