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

package info.gianlucacosta.knapscal.knapsack

import org.scalatest.{FlatSpec, Matchers}


class ItemSpec extends FlatSpec with Matchers {
  "Items" should "be ordered by profit/weight" in {
    val itemA = new Item(4, 2)
    val itemB = new Item(6, 1)

    itemB.compare(itemA) should be(-1)
  }


  "A sequence of items" should "be correctly ordered" in {
    val unsortedList = Seq(
      Item(4, 2),
      Item(12, 12),
      Item(5, 1)
    )

    unsortedList.sorted should be(Seq(
      Item(5, 1),
      Item(4, 2),
      Item(12, 12)
    ))
  }
}

