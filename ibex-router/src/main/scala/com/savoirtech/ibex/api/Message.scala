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

case class Message(body: AnyRef, headers: Map[String, AnyVal] = Map()) {
  /**
   * Creates a copy of this Message with a new body.
   * @param newBody the new body
   * @return a copy of this Message with a new body
   */
  def withBody(newBody: AnyRef): Message = Message(newBody, headers)

  /**
   * Creates a copy of this Message with exact new headers.
   * @param value the new headers
   * @return a copy of this Message with exact new headers
   */
  def withHeaders(value: Map[String, AnyVal]): Message = Message(body, value)

  /**
   * Creates a copy of this Message with new headers appended (as key/value pairs)
   * @param newHeaders the new headers (as key/value pairs)
   * @return a copy of this Message with new headers appended
   */
  def withHeaders(newHeaders: (String, AnyVal)*): Message = Message(body, headers ++ newHeaders)


  /**
   * Creates a copy of this Message with a specific header value.
   * @param key the header key
   * @param value the header value
   * @return a copy of this Message with a specific header value
   */
  def withHeader(key: String, value: AnyVal): Message = Message(body, headers + (key -> value))
}
