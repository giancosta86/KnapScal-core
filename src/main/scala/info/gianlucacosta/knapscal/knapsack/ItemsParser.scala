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

object ItemsParser {
  private val itemRegex = """\s*(\d+)\s*\|\s*(\d+)\s*""".r
}


class ItemsParser {
  private var latestInputText = ""
  private var latestResult: Seq[Item] = List()

  def parse(inputText: String): Seq[Item] = {
    if (inputText == latestInputText) {
      return latestResult
    }

    latestResult = inputText
      .split("\n")
      .map(line => line.trim)
      .filter(line => !line.isEmpty)
      .map(line => {
      val matchResult = ItemsParser.itemRegex.findFirstMatchIn(line)

      if (matchResult.isEmpty) {
        throw new IllegalArgumentException(s"Invalid item format: '${line}'")
      }

      val matcher = matchResult.get
      Item(
        matcher.group(1).toInt,
        matcher.group(2).toInt
      )
    })

    latestInputText = inputText


    return latestResult
  }
}
