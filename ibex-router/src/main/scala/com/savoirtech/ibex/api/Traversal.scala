/*
 * Copyright (C) 2014 Savoir Technologies, Inc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.savoirtech.ibex.api

trait Traversal {
  /**
   * The current message
   * @return the current message
   */
  def message: Message

  /**
   * Proceed with the current Traversal using the current message
   */
  def proceed(): Unit = proceed(message)

  /**
   * Proceed with the current Traversal using the specified message 
   * @param message the message
   */
  def proceed(message: Message): Unit

  /**
   * Inject a step (by URI) into the current Traversal using the specified message
   * @param uri the URI
   * @param message the message
   */
  def inject(uri: String, message: Message): Unit

  /**
   * Inject a step (by URI) into the current Traversal
   * @param uri the URI
   */
  def inject(uri: String): Unit

  /**
   * Inject a path into the current Traversal using the specified message
   * @param path the path
   * @param message the message
   */
  def inject(path: Path, message: Message): Unit

  /**
   * Inject a path into the current Traversal
   * @param path the path
   */
  def inject(path: Path)
}
