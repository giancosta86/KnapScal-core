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

package info.gianlucacosta.knapscal.knapsack

case class Problem(
                    unsortedItems: Seq[Item],
                    capacity: Int
                    ) {
  require(unsortedItems.nonEmpty, "There must be at least one item")
  require(capacity > 0, s"Capacity ${capacity} must be >= 0")
  unsortedItems.foreach(item => require(capacity >= item.weight, s"Weight ${item.weight} exceeds capacity"))

  val items = unsortedItems.sorted

  def oneBasedItems = items.zipWithIndex.map { case (item, index) => (index + 1, item) }.toMap
}