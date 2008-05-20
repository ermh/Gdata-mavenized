/* Copyright (c) 2006 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.data.health;

import com.google.gdata.data.BaseEntry;

/**
 * Describes a health entry.
 *
 * @param <E> concrete entry type
 * 
 */
public abstract class BaseHealthEntry<E extends BaseHealthEntry> extends
    BaseEntry<E> {

  /**
   * Default mutable constructor.
   */
  public BaseHealthEntry() {
    super();
  }

  /**
   * Constructs a new instance by doing a shallow copy of data from an existing
   * {@link BaseEntry} instance.
   *
   * @param sourceEntry source entry
   */
  public BaseHealthEntry(BaseEntry sourceEntry) {
    super(sourceEntry);
  }

  @Override
  public String toString() {
    return "{BaseHealthEntry " + super.toString() + "}";
  }

}
