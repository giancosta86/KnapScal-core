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

import org.apache.commons.math3.fraction.Fraction

case class Item(profit: Int, weight: Int) extends Ordered[Item] {
  require(profit > 0, s"Profit ${profit} must be >= 0")
  require(weight > 0, s"Weight ${weight} must be >= 0")

  val relativeProfit: Fraction = new Fraction(profit, weight)

  override def compare(that: Item): Int = -relativeProfit.compareTo(that.relativeProfit)

  override def toString: String = s"(${profit}, ${weight})"
}
