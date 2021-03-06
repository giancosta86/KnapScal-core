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

import scala.collection.mutable.ListBuffer

private class NodeBuffer {
  private val buffer = ListBuffer[Node]()

  def +=(node: Node): Unit = {
    require(node.index == buffer.size, s"Node index ${node.index} != ${buffer.size}")

    buffer += node
  }

  def clear() = buffer.clear

  def size() = buffer.size

  def toList = buffer.toList
}
