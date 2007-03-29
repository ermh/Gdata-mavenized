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

package com.google.api.gbase.client;

import com.google.gdata.data.BaseEntry;

import java.util.List;

/**
 * A description of an item type done by the gm: tags in a
 * google base entry.
 *
 * Make sure the current feed is an item type feed and then get the
 * ItemTypeDescription for the entry using:
 * {@link GoogleBaseEntry#getGoogleBaseMetadata()}.getItemTypeDescription()
 */
public class ItemTypeDescription {

  private final BaseEntry owner;

  /**
   * Creates an ItemTypeDescription.
   *
   * @param owner entry the description is in
   */
  ItemTypeDescription(BaseEntry owner) {
    this.owner = owner;
  }

  void setName(String name) {
    if (name == null) {
      owner.removeExtension(GmItemType.class);
    } else {
      owner.setExtension(new GmItemType(name));
    }
  }
  
  /**
   * Gets the item type name.
   */
  public String getName() {
    GmItemType tag = owner.getExtension(GmItemType.class);
    return tag == null ? null : tag.getItemType();
  }

  /**
   * Get the list of attribute ids defined for this item type.
   *
   * @return modifiable list of attribute ids
   */
  public List<? extends GoogleBaseAttributeId> getAttributeIds() {
    GmAttributes attrs = owner.getExtension(GmAttributes.class);
    if (attrs == null) {
      attrs = new GmAttributes();
      owner.setExtension(attrs);
    }
    return attrs.getAttributeIds();
  }
}
