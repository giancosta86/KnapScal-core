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

package info.gianlucacosta.knapscal.knapsack.dynamic.full

trait Iteration {
  val activeStates: Seq[State]
  val dominatedStates: Set[State]

  override def equals(obj: scala.Any): Boolean = obj match {
    case iteration: Iteration => activeStates == iteration.activeStates && dominatedStates == iteration.dominatedStates
    case _ => false
  }

  override def hashCode(): Int = activeStates.hashCode
}
